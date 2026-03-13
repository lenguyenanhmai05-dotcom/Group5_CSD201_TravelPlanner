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
    }

    // GET /api/customer/{id} — Search for a customer in BST by ID
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

    // POST /api/customer/add — Insert a new customer into BST
    public void add(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            String phone = body.getOrDefault("phone", "").toString();

            Customer customer = new Customer(id, name, phone);
            bst.insert(customer);
            ctx.status(201).json(Map.of("message", "Customer " + name + " inserted into BST successfully"));
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Invalid request: " + e.getMessage()));
        }
    }

    // DELETE /api/customer/{id} — Delete a customer from BST
    public void delete(Context ctx) {
        String id = ctx.pathParam("id");
        bst.delete(id);
        ctx.json(Map.of("message", "Customer " + id + " deleted from BST"));
    }
}
