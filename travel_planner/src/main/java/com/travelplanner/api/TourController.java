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

    // POST /api/tour/add — Add a new location (JSON body: {id, name, description,
    // price})
    public void add(Context ctx) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = mapper.readValue(ctx.body(), Map.class);
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            String description = body.getOrDefault("description", "").toString();
            double price = body.containsKey("price") ? ((Number) body.get("price")).doubleValue() : 0.0;

            TourLocation loc = new TourLocation(id, name, description, price);
            tour.addLast(loc);
            ctx.status(201).json(Map.of("message", "Added " + name + " successfully"));
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "Invalid request: " + e.getMessage()));
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
