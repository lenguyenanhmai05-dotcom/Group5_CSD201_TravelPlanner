package com.travelplanner.structures.graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    private Graph graph;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        graph = new Graph();
        // Redirect
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Restore original streams
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testAddLocationAndEdge() {
        Location locA = new Location("Quy Nhon", "Quy Nhon City");
        Location locB = new Location("Dieu Tri", "Dieu Tri Station");

        graph.addLocation(locA);
        graph.addLocation(locB);
        graph.addEdge("Quy Nhon", "Dieu Tri", 10.5);

        graph.printGraph();
        String output = outContent.toString();

        assertTrue(output.contains("Quy Nhon City is connected to: Dieu Tri Station (10.5km)"),
                "Node A should be connected to Node B");
        assertTrue(output.contains("Dieu Tri Station is connected to: Quy Nhon City (10.5km)"),
                "Node B should be connected to Node A");
    }

    @Test
    public void testLoadFromFile() {

        String testFilePath = "map_test.txt";
        graph.loadFromFile(testFilePath);

        String output = outContent.toString();
        assertTrue(output.contains("Successfully loaded map data from: " + testFilePath),
                "Should successfully load file");

        outContent.reset();
        graph.printGraph();
        String graphOutput = outContent.toString();

        assertTrue(graphOutput.contains("Quy Nhon is connected to:"), "Quy Nhon should have connections");
        assertTrue(graphOutput.contains("Song Cau (20.0km)"), "Should contain edge to Song Cau");
        assertTrue(graphOutput.contains("Chi Thanh (60.0km)"), "Should contain edge to Chi Thanh");
    }

    @Test
    public void testDijkstraShortestPath() {

        graph.loadFromFile("map_test.txt");
        outContent.reset();

        // Run Dijkstra from Quy Nhon to Tuy Hoa
        // Expected optimal path: Quy Nhon -> Song Cau -> Chi Thanh -> Tuy Hoa
        // Distances: 20.0 + 30.0 + 40.0 = 90.0 km
        // Note: The direct-ish route Quy Nhon -> Song Cau -> Tuy Hoa is 120.0 km
        // And Quy Nhon -> Chi Thanh -> Tuy Hoa is 100.0 km
        graph.dijkstra("Quy Nhon", "Tuy Hoa");

        String output = outContent.toString();

        // Assert distance
        assertTrue(output.contains("Total distance: 90.0 km"), "The shortest distance should be 90.0 km");
        // Assert path order
        assertTrue(output.contains("Path: Quy Nhon -> Song Cau -> Chi Thanh -> Tuy Hoa"),
                "The path should be exactly Quy Nhon -> Song Cau -> Chi Thanh -> Tuy Hoa");
    }

    @Test
    public void testDijkstraUnreachablePath() {
        graph.addLocation(new Location("Ky Co", "Ky Co Beach"));
        graph.addLocation(new Location("Eo Gio", "Eo Gio"));
        // No edges between them

        graph.dijkstra("Ky Co", "Eo Gio");
        String output = outContent.toString();

        assertTrue(output.contains("No path exists from Ky Co Beach to Eo Gio"), "Should state that no path exists");
    }

    @Test
    public void testDijkstraInvalidNode() {
        graph.dijkstra("SAI GON", "HA NOI");
        String errOutput = errContent.toString();

        assertTrue(errOutput.contains("Start or end node does not exist!"), "Should print error for invalid nodes");
    }

    @Test
    public void testAddEdgeInvalidNode() {

        graph.addLocation(new Location("Quy Nhon", "Quy Nhon City"));
        graph.addEdge("Quy Nhon", "B", 10);

        String err = errContent.toString();
        assertTrue(err.contains("One of the nodes does not exist!"));
    }
}
