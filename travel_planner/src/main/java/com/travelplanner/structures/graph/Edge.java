package com.travelplanner.structures.graph;

public class Edge {
    private String destinationId;
    private double weight;

    public Edge(String destinationId, double weight) {
        this.destinationId = destinationId;
        this.weight = weight;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "->" + destinationId + " (" + weight + "km)";
    }
}
