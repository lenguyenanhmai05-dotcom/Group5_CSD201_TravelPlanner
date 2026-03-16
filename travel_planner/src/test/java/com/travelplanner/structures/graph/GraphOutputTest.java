package com.travelplanner.structures.graph;

import org.junit.jupiter.api.Test;

public class GraphOutputTest {

    @Test
    public void runGraphDemo() {
        System.out.println("\n=== TRAVEL PLANNER: GRAPH ALGORITHM OUTPUT ===");

        Graph graph = new Graph();

        // 1. Load data from file
        System.out.println("\n[Phase 1: Loading Map Data]");
        graph.loadFromFile("map_test.txt");

        // 2. Print the graph structure
        System.out.println("\n[Phase 2: Graph Structure]");
        graph.printGraph();

        // 3. Test Dijkstra Shortest Path
        System.out.println("\n[Phase 3: Shortest Path Calculation]");

        System.out.println("\n> Route 1: Quy Nhon -> Tuy Hoa");
        graph.dijkstra("Quy Nhon", "Tuy Hoa");

        // System.out.println("\n> Route 2: Hue -> Nha Trang");
        // graph.dijkstra("Hue", "Nha Trang");

        // System.out.println("\n> Route 3: Da Nang -> Tuy Hoa");
        // graph.dijkstra("Da Nang", "Tuy Hoa");

        System.out.println("\n> Route 4: Unreachable Path (Ky Co -> Eo Gio)");
        graph.addLocation(new Location("Ky Co", "Ky Co Beach"));
        graph.addLocation(new Location("Eo Gio", "Eo Gio Beach"));
        // Không thêm đường nối
        graph.dijkstra("Ky Co", "Eo Gio");

        System.out.println("\n> Route 5: Invalid Node (SAI GON -> HA NOI)");
        graph.dijkstra("SAI GON", "HA NOI");

        System.out.println("\n=== END OF OUTPUT ===\n");
    }
}
