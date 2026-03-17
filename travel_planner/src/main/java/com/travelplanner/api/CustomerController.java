package com.travelplanner.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelplanner.entities.Customer;
import com.travelplanner.structures.BinarySearchTree;

import io.javalin.http.Context;

import java.util.Map;

public class CustomerController {

    private final BinarySearchTree bst;
    private final ObjectMapper mapper = new ObjectMapper();

    public CustomerController(BinarySearchTree bst) {
        this.bst = bst;
        seedData();
    }

    private void seedData() {
        // Bộ dữ liệu mẫu "Hoàn hảo" để demo cây cân đối
        // Gốc (Root) là M100 - nằm chính giữa
        bst.insert(new Customer("M100", "Minh Trung", "0901112223"));
        
        // Nhánh trái (Tên và ID nhỏ hơn M)
        bst.insert(new Customer("G050", "Gia Bao", "0904445556"));
        bst.insert(new Customer("D025", "Duy An", "0907778889"));
        bst.insert(new Customer("J075", "Huu Loc", "0902223334"));
        
        // Nhánh phải (Tên và ID lớn hơn M)
        bst.insert(new Customer("S150", "Son Ha", "0905556667"));
        bst.insert(new Customer("P125", "Phuong Nam", "0908889990"));
        bst.insert(new Customer("V200", "Viet Tien", "0909990001"));
    }

    // GET /api/customer — Search for a customer in BST by ID
    public void search(Context ctx) {
        String id = ctx.pathParam("id");
        Customer customer = bst.search(id);
        if (customer != null) {
            ctx.json(Map.of(
                    "id", customer.getId(),
                    "name", customer.getName(),
                    "phone", customer.getPhone()));
        } else {
            ctx.status(404).json(Map.of("error", "Customer with ID " + id + " not found in BST"));
        }
    }

    // GET /api/customer/all — Get all customers sorted by ID
    public void getAll(Context ctx) {
        ctx.json(bst.getAllInOrder());
    }

    // GET /api/customer/tree — Get the whole BST structure for visualization
    public void getTree(Context ctx) {
        ctx.json(bst.getRoot());
    }

    // POST /api/customer/add — Insert a new customer into BST
    public void add(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            String phone = body.getOrDefault("phone", "").toString();

            // Validation: ID and Name are required
            if (id == null || id.isBlank() || name == null || name.isBlank()) {
                ctx.status(400).json(Map.of("error", "Vui lòng nhập đầy đủ ID và Họ tên!"));
                return;
            }

            // Validation: Phone number must be 10 digits
            if (!phone.matches("\\d{10}")) {
                ctx.status(400).json(Map.of("error", "Số điện thoại phải là 10 chữ số!"));
                return;
            }

            Customer customer = new Customer(id, name, phone);
            boolean inserted = bst.insert(customer);
            
            if (inserted) {
                ctx.status(201).json(Map.of("message", "Customer " + name + " inserted into BST successfully"));
            } else {
                ctx.status(409).json(Map.of("error", "Mã ID '" + id + "' đã tồn tại trong hệ thống!"));
            }
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Lỗi dữ liệu: " + e.getMessage()));
        }
    }

    // DELETE /api/customer/{id} — Delete a customer from BST
    public void delete(Context ctx) {
        String id = ctx.pathParam("id");
        bst.delete(id);
        ctx.json(Map.of("message", "Customer " + id + " deleted from BST"));
    }
}
