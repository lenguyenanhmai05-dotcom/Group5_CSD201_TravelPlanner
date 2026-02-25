package com.travelplanner.structures.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
    private Map<String, Location> locations;
    private Map<String, List<Edge>> adjList;

    public Graph() {
        locations = new HashMap<>();
        adjList = new HashMap<>();
    }

    public void addLocation(Location location) {
        locations.put(location.getId(), location);
        adjList.putIfAbsent(location.getId(), new ArrayList<>());
    }

    // Undirected Graph, so add edges in both directions
    public void addEdge(String sourceId, String destinationId, double weight) {
        if (!adjList.containsKey(sourceId) || !adjList.containsKey(destinationId)) {
            System.err.println("One of the nodes does not exist!");
            return;
        }
        adjList.get(sourceId).add(new Edge(destinationId, weight));
        adjList.get(destinationId).add(new Edge(sourceId, weight)); // Because it is an undirected edge
    }

    public void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Skip empty lines and comments
                }

                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String u = parts[0].trim();
                    String v = parts[1].trim();
                    double w = Double.parseDouble(parts[2].trim());

                    // Register locations if they don't exist yet
                    if (!locations.containsKey(u)) {
                        addLocation(new Location(u, u)); // ID and Name are the same
                    }
                    if (!locations.containsKey(v)) {
                        addLocation(new Location(v, v));
                    }

                    addEdge(u, v, w);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }

            System.out.println("Successfully loaded map data from: " + filePath);
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Number format error in file: " + e.getMessage());
        }
    }

    public void printGraph() {
        for (String locId : locations.keySet()) {
            Location loc = locations.get(locId);
            System.out.print(loc.getName() + " is connected to: ");
            List<Edge> edges = adjList.get(locId);
            for (Edge edge : edges) {
                Location dest = locations.get(edge.getDestinationId());
                System.out.print(dest.getName() + " (" + edge.getWeight() + "km) | ");
            }
            System.out.println();
        }
    }

    // Helper class for PriorityQueue in Dijkstra
    private static class RouteNode implements Comparable<RouteNode> {
        String id;
        double distance;

        public RouteNode(String id, double distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(RouteNode other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public void dijkstra(String startId, String endId) {
        if (!locations.containsKey(startId) || !locations.containsKey(endId)) {
            System.err.println("Start or end node does not exist!");
            return;
        }

        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>(); // To store the path
        PriorityQueue<RouteNode> pq = new PriorityQueue<>();

        // Initialize distances to infinity
        for (String locId : locations.keySet()) {
            distances.put(locId, Double.MAX_VALUE);
            previousNodes.put(locId, null);
        }

        // Start from the startId
        distances.put(startId, 0.0);
        pq.add(new RouteNode(startId, 0.0));

        while (!pq.isEmpty()) {
            RouteNode current = pq.poll();
            String currentId = current.id;

            // Early exit if we reached the end node
            if (currentId.equals(endId)) {
                break;
            }

            // Skip if the extracted distance is larger than the recorded distance
            // (Optimization)
            if (current.distance > distances.get(currentId))
                continue;

            // Traverse the neighbors
            if (adjList.containsKey(currentId)) {
                for (Edge edge : adjList.get(currentId)) {
                    String neighborId = edge.getDestinationId();
                    double newDist = distances.get(currentId) + edge.getWeight();

                    // If a shorter path is found (Relaxation)
                    if (newDist < distances.get(neighborId)) {
                        distances.put(neighborId, newDist);
                        previousNodes.put(neighborId, currentId);
                        pq.add(new RouteNode(neighborId, newDist));
                    }
                }
            }
        }

        // Print the result
        printPath(startId, endId, distances, previousNodes);
    }

    private void printPath(String startId, String endId, Map<String, Double> distances,
            Map<String, String> previousNodes) {
        if (distances.get(endId) == Double.MAX_VALUE) {
            System.out.println("No path exists from " + locations.get(startId).getName() + " to "
                    + locations.get(endId).getName());
            return;
        }

        List<String> path = new ArrayList<>();
        String current = endId;
        while (current != null) {
            path.add(current);
            current = previousNodes.get(current);
        }

        Collections.reverse(path); // Reverse the list to get the order from start to end

        System.out.println("\n=== OPTIMAL RECOMMENDED ROUTE ===");
        System.out.println("From: " + locations.get(startId).getName());
        System.out.println("To: " + locations.get(endId).getName());
        System.out.println("Total distance: " + distances.get(endId) + " km");
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(locations.get(path.get(i)).getName());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\n=================================");
    }
}
