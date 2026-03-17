package com.travelplanner.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.LocationNode;
import com.travelplanner.structures.TourLinkedList;

import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TourController {

    private final TourLinkedList tour;
    private final ObjectMapper mapper = new ObjectMapper();

    public TourController(TourLinkedList tour) {
        this.tour = tour;
    }

    // GET /api/tour — Return all locations in the tour as JSON array
    public void getAll(Context ctx) {
        List<Map<String, Object>> result = new ArrayList<>();
        LocationNode curr = tour.getHead();
        while (curr != null) {
            TourLocation loc = curr.info;
            result.add(Map.of(
                    "id", loc.getId(),
                    "name", loc.getName(),
                    "description", loc.getDescription(),
                    "price", loc.getPrice()));
            curr = curr.next;
        }
        ctx.json(result);
    }

    // POST /api/tour/add — Add a new location at the END (Add Last)
    public void addLast(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            String description = body.getOrDefault("description", "").toString();
            double price = body.containsKey("price") ? ((Number) body.get("price")).doubleValue() : 0.0;

            TourLocation loc = new TourLocation(id, name, description, price);
            tour.addLast(loc);
            ctx.status(201).json(Map.of("message", "Đã thêm " + name + " vào cuối lịch trình"));
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Lỗi: " + e.getMessage()));
        }
    }

    // POST /api/tour/addFirst — Add a new location at the START (Add First)
    public void addFirst(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            String description = body.getOrDefault("description", "").toString();
            double price = body.containsKey("price") ? ((Number) body.get("price")).doubleValue() : 0.0;

            TourLocation loc = new TourLocation(id, name, description, price);
            tour.addFirst(loc);
            ctx.status(201).json(Map.of("message", "Đã chèn " + name + " vào đầu lịch trình"));
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Lỗi: " + e.getMessage()));
        }
    }

    // POST /api/tour/insert — Insert a new location AFTER a specific ID
    public void insertAfter(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String afterId = (String) body.get("afterId");
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            String description = body.getOrDefault("description", "").toString();
            double price = body.containsKey("price") ? ((Number) body.get("price")).doubleValue() : 0.0;

            TourLocation loc = new TourLocation(id, name, description, price);
            boolean success = tour.insertAfter(afterId, loc);
            if (success) {
                ctx.status(201).json(Map.of("message", "Đã chèn " + name + " sau điểm " + afterId));
            } else {
                ctx.status(404).json(Map.of("error", "Không tìm thấy điểm ID: " + afterId));
            }
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Lỗi: " + e.getMessage()));
        }
    }

    // POST /api/tour/batch-add — Add multiple locations from a list
    public void batchAdd(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> locations = mapper.readValue(ctx.body(), List.class);
            for (Map<String, Object> item : locations) {
                String id = (String) item.get("id");
                String name = (String) item.get("name");
                String description = item.getOrDefault("description", "").toString();
                double price = item.containsKey("price") ? ((Number) item.get("price")).doubleValue() : 0.0;
                
                tour.addLast(new TourLocation(id, name, description, price));
            }
            ctx.status(201).json(Map.of("message", "Đã lưu " + locations.size() + " điểm vào lịch trình!"));
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Lỗi lưu hàng loạt: " + e.getMessage()));
        }
    }

    // PUT /api/tour/update — Update a location's details
    public void update(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String id = (String) body.get("id");
            String description = (String) body.get("description");
            double price = ((Number) body.get("price")).doubleValue();

            boolean updated = tour.update(id, description, price);
            if (updated) {
                ctx.json(Map.of("message", "Đã cập nhật điểm " + id + " thành công!"));
            } else {
                ctx.status(404).json(Map.of("error", "Không tìm thấy điểm ID: " + id));
            }
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Lỗi cập nhật: " + e.getMessage()));
        }
    }

    // DELETE /api/tour/{id} — Remove a location by ID
    public void remove(Context ctx) {
        String id = ctx.pathParam("id");
        boolean removed = tour.removeById(id);
        if (removed) {
            ctx.json(Map.of("message", "Removed location " + id + " successfully"));
        } else {
            ctx.status(404).json(Map.of("error", "Location with ID " + id + " not found"));
        }
    }
}
