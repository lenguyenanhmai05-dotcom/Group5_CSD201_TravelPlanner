package com.travelplanner.app;

import com.travelplanner.entities.Customer;
import com.travelplanner.structures.BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BenchmarkApp {

    public static void main(String[] args) {
        System.out.println("=== BENCHMARK: LINKED LIST (JAVA LIST) vs BINARY SEARCH TREE ===");

        int DATABOX_SIZE = 10000;
        int SEARCH_TEST_COUNT = 1000;

        // 1. Generate Data
        System.out.println("Generating " + DATABOX_SIZE + " customer records...");
        List<Customer> dataPool = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < DATABOX_SIZE; i++) {
            // Random ID like "CUS-12345"
            String id = "CUS-" + String.format("%05d", i);
            // Random Name
            String name = "Customer " + i;
            // Random Phone
            String phone = "090" + String.format("%07d", rand.nextInt(9999999));

            dataPool.add(new Customer(id, name, phone));
        }

        // Shuffle to ensure random insertion order for BST (prevent skewed tree)
        Collections.shuffle(dataPool);

        // 2. Setup Structures
        List<Customer> linearList = new java.util.LinkedList<>(); // Changed to LinkedList as requested
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("Inserting data into structures...");
        long startInit = System.nanoTime();

        for (Customer c : dataPool) {
            linearList.add(c);
            bst.insert(c);
        }
        long endInit = System.nanoTime();
        System.out.println("Insertion completed in " + ((endInit - startInit) / 1_000_000) + " ms");

        // 3. Prepare Search Keys (Pick random existing IDs)
        List<String> searchKeys = new ArrayList<>();
        for (int i = 0; i < SEARCH_TEST_COUNT; i++) {
            Customer randomCus = dataPool.get(rand.nextInt(DATABOX_SIZE));
            searchKeys.add(randomCus.getId());
        }

        // 4. Benchmark Linear Search (List)
        System.out.println("\n>> Running Linear Search on LinkedList (" + SEARCH_TEST_COUNT + " searches)...");
        long startList = System.nanoTime();

        int foundList = 0;
        for (String searchId : searchKeys) {
            for (Customer c : linearList) {
                if (c.getId().equals(searchId)) {
                    foundList++;
                    break;
                }
            }
        }

        long endList = System.nanoTime();
        double timeList = (endList - startList) / 1_000_000.0;
        System.out.println("Result: Found " + foundList + "/" + SEARCH_TEST_COUNT);
        System.out.println("Time: " + timeList + " ms");

        // 5. Benchmark BST Search
        System.out.println("\n>> Running Binary Search on BST (" + SEARCH_TEST_COUNT + " searches)...");
        long startBST = System.nanoTime();

        int foundBST = 0;
        for (String searchId : searchKeys) {
            Customer c = bst.search(searchId);
            if (c != null) {
                foundBST++;
            }
        }

        long endBST = System.nanoTime();
        double timeBST = (endBST - startBST) / 1_000_000.0;
        System.out.println("Result: Found " + foundBST + "/" + SEARCH_TEST_COUNT);
        System.out.println("Time: " + timeBST + " ms");

        // 6. Conclusion
        System.out.println("\n=================================");
        System.out.println("CONCLUSION:");
        if (timeBST < timeList) {
            double ratio = timeList / timeBST;
            System.out.printf("BST is %.2f times faster than List.\n", ratio);
        } else {
            System.out.println("List is faster (Unexpected for large dataset).");
        }
    }
}
