package com.travelplanner.app;

import com.travelplanner.api.CustomerController;
import com.travelplanner.api.MapController;
import com.travelplanner.api.TourController;
import com.travelplanner.entities.Customer;
import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.BinarySearchTree;
import com.travelplanner.structures.TourLinkedList;
import com.travelplanner.structures.graph.Graph;

import io.javalin.Javalin;

public class WebApp {

    public static void main(String[] args) {
        // ─── Initialize Data Structures with sample data ─────────────────────────────

        // 1. Linked List - Tour schedule
        TourLinkedList tour = new TourLinkedList();
        tour.addLast(new TourLocation("QN", "Quy Nhon", "Thanh pho bien dep nhat Binh Dinh", 200.0));
        tour.addLast(new TourLocation("KY", "Ky Co", "Bai bien hoang so tuyet voi", 100.0));
        tour.addLast(new TourLocation("SC", "Song Cau", "Thi xa ven bien Phu Yen", 150.0));
        tour.addLast(new TourLocation("TH", "Tuy Hoa", "Thu do cua Phu Yen", 120.0));

        // 2. BST - Customer database
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(new Customer("C001", "Nguyen Van An", "0901234567"));
        bst.insert(new Customer("C003", "Tran Thi Bich", "0912345678"));
        bst.insert(new Customer("C002", "Le Minh Duc", "0987654321"));
        bst.insert(new Customer("C005", "Pham Thi Lan", "0977123456"));
        bst.insert(new Customer("C004", "Hoang Van Nam", "0966234567"));

        // 3. Graph - Map with Dijkstra routes
        Graph graph = new Graph();
        graph.loadFromFile("map_test.txt");

        // ─── Build Controllers
        // ────────────────────────────────────────────────────────
        TourController tourCtrl = new TourController(tour);
        CustomerController customerCtrl = new CustomerController(bst);
        MapController mapCtrl = new MapController(graph);

        // ─── Start Javalin Web Server
        // ─────────────────────────────────────────────────
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public"); // Serve HTML/CSS/JS from resources/public
            config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()));
        });

        // Tour routes (Linked List)
        app.get("/api/tour", tourCtrl::getAll);
        app.post("/api/tour/add", tourCtrl::add);
        app.delete("/api/tour/{id}", tourCtrl::remove);

        // Customer routes (BST)
        app.get("/api/customer/{id}", customerCtrl::search);
        app.post("/api/customer/add", customerCtrl::add);
        app.delete("/api/customer/{id}", customerCtrl::delete);

        // Map routes (Graph/Dijkstra)
        app.get("/api/map/route", mapCtrl::findRoute);

        app.start(8080);
        System.out.println("\n✅ Travel Planner Web App running at http://localhost:8080");
    }
}
