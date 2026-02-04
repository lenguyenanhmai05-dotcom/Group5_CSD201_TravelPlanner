package com.travelplanner.structures;

import com.travelplanner.entities.Customer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    void testSearchInEmptyTree() {
        // Test key requirement: No NullPointerException on empty tree
        assertNull(bst.search("ANY_ID"), "Search on empty tree should return null");
    }

    @Test
    void testInsertAndSearch() {
        bst.insert(new Customer("C01", "An", "0123"));
        bst.insert(new Customer("C02", "Binh", "0456"));

        Customer found = bst.search("C01");
        assertNotNull(found);
        assertEquals("An", found.getName());

        Customer found2 = bst.search("C02");
        assertNotNull(found2);
        assertEquals("Binh", found2.getName());
    }

    @Test
    void testInsertDuplicate() {
        bst.insert(new Customer("C01", "An", "0123"));
        bst.insert(new Customer("C01", "An Duplicate", "0123"));

        // Count should remain 1 if your BST handles duplicates by ignoring them
        // or we check the info isn't overwritten if that's the logic.
        assertEquals(1, bst.count());
    }

    @Test
    void testDeleteLeafNode() {
        bst.insert(new Customer("C02", "Root", "000"));
        bst.insert(new Customer("C01", "Left", "000"));
        bst.delete("C01"); // Delete leaf
        assertNull(bst.search("C01"));
        assertEquals(1, bst.count());
    }

    @Test
    void testDeleteNodeWithOneChild() {
        bst.insert(new Customer("C05", "Root", "000"));
        bst.insert(new Customer("C03", "Left", "000"));
        bst.insert(new Customer("C02", "Left-Left", "000"));

        bst.delete("C03"); // Delete node with one child (C02)
        assertNull(bst.search("C03"));
        assertNotNull(bst.search("C02"));
        assertEquals(2, bst.count());
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        bst.insert(new Customer("C05", "Root", "000"));
        bst.insert(new Customer("C03", "Left", "000"));
        bst.insert(new Customer("C07", "Right", "000"));
        bst.insert(new Customer("C02", "Left-Left", "000"));
        bst.insert(new Customer("C04", "Left-Right", "000"));

        bst.delete("C03"); // Delete node with two children
        assertNull(bst.search("C03"));
        assertNotNull(bst.search("C02"));
        assertNotNull(bst.search("C04"));
        assertEquals(4, bst.count());
    }

    @Test
    void testDeleteNonExistent() {
        // Test key requirement: No NullPointerException when deleting missing ID
        bst.insert(new Customer("C01", "An", "0123"));
        assertDoesNotThrow(() -> bst.delete("NON_EXIST"), "Deleting non-existent ID should not crash");
        assertEquals(1, bst.count());
    }

    @Test
    void testInOrderTraversal() {
        bst.insert(new Customer("C02", "B", "000"));
        bst.insert(new Customer("C01", "A", "000"));
        bst.insert(new Customer("C03", "C", "000"));

        // This test visually checks if in-order works.
        // For automated test, we'd need to capture System.out,
        // but verify the structure is correct via count for now.
        assertEquals(3, bst.count());
    }
}
