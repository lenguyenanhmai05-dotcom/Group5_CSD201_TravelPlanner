package com.travelplanner.api;

import com.travelplanner.structures.graph.Graph;
import io.javalin.http.Context;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapController {

    private final Graph graph;

    public MapController(Graph graph) {
        this.graph = graph;
    }

    // GET /api/map/route?start=X&end=Y — Find shortest path using Dijkstra
    public void findRoute(Context ctx) {
        String start = ctx.queryParam("start");
        String end = ctx.queryParam("end");

        if (start == null || end == null || start.isBlank() || end.isBlank()) {
            ctx.status(400).json(Map.of("error", "Please provide both 'start' and 'end' query parameters"));
            return;
        }

        // Capture Dijkstra output from System.out
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        ByteArrayOutputStream errBaos = new ByteArrayOutputStream();
        PrintStream errPs = new PrintStream(errBaos);

        System.setOut(ps);
        System.setErr(errPs);
        graph.dijkstra(start, end);
        System.setOut(oldOut);
        System.setErr(oldErr);

        String output = baos.toString();
        String errOutput = errBaos.toString();

        if (!errOutput.isBlank() || output.contains("No path exists")) {
            String message = output.contains("No path exists")
                    ? output.trim()
                    : "Start or destination city not found. Check spelling.";
            ctx.status(404).json(Map.of("error", message));
            return;
        }

        // Parse the output to extract structured data
        String distance = "";
        String path = "";

        Matcher distMatcher = Pattern.compile("Total distance: (.+) km").matcher(output);
        if (distMatcher.find())
            distance = distMatcher.group(1) + " km";

        Matcher pathMatcher = Pattern.compile("Path: (.+)").matcher(output);
        if (pathMatcher.find())
            path = pathMatcher.group(1).trim();

        // Build path steps list
        List<String> steps = new ArrayList<>();
        if (!path.isEmpty()) {
            for (String step : path.split(" -> ")) {
                steps.add(step.trim());
            }
        }

        ctx.json(Map.of(
                "start", start,
                "end", end,
                "distance", distance,
                "path", path,
                "steps", steps));
    }
}
