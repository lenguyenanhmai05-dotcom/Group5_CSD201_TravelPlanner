package com.travelplanner.structures;

import com.travelplanner.entities.Customer;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    // 1. Insert (Recursion) - Returns true if inserted, false if duplicate
    private boolean lastInsertSuccess = false;

    public boolean insert(Customer customer) {
        lastInsertSuccess = false;
        root = insertRec(root, customer);
        return lastInsertSuccess;
    }

    private Node insertRec(Node root, Customer customer) {
        if (root == null) {
            root = new Node(customer);
            lastInsertSuccess = true;
            return root;
        }

        // Compare IDs: if customer ID < root ID -> goes Left
        if (customer.compareTo(root.info) < 0) {
            root.left = insertRec(root.left, customer);
        }
        // if customer ID > root ID -> goes Right
        else if (customer.compareTo(root.info) > 0) {
            root.right = insertRec(root.right, customer);
        }
        // If IDs are equal, we don't insert (silently ignore, lastInsertSuccess remains false)

        return root;
    }

    // 2. Search (Recursion)
    public Customer search(String id) {
        Node result = searchRec(root, id);
        if (result != null) {
            return result.info;
        }
        return null;
    }

    private Node searchRec(Node root, String id) {
        if (root == null || root.info.getId().equals(id)) {
            return root;
        }

        // Val is greater than root's key -> Search Right
        if (id.compareTo(root.info.getId()) > 0) {
            return searchRec(root.right, id);
        }

        // Val is smaller than root's key -> Search Left
        return searchRec(root.left, id);
    }

    // 3. Traversal (In-Order: Left -> Root -> Right)
    // Used to print sorted list
    public void inOrder() {
        inOrderRec(root);
        System.out.println("END");
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.info);
            inOrderRec(root.right);
        }
    }

    // 4. Count nodes
    public int count() {
        return countRec(root);
    }

    private int countRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countRec(root.left) + countRec(root.right);
    }

    // 5. Get all customers (In-Order: Sorted by ID)
    public List<Customer> getAllInOrder() {
        List<Customer> list = new ArrayList<>();
        getAllInOrderRec(root, list);
        return list;
    }

    private void getAllInOrderRec(Node node, List<Customer> list) {
        if (node != null) {
            getAllInOrderRec(node.left, list);
            list.add(node.info);
            getAllInOrderRec(node.right, list);
        }
    }

    // 6. Delete (Recursion)
    public void delete(String id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, String id) {
        if (root == null) {
            return root;
        }

        // Found the node to be deleted
        if (id.compareTo(root.info.getId()) < 0) {
            root.left = deleteRec(root.left, id);
        } else if (id.compareTo(root.info.getId()) > 0) {
            root.right = deleteRec(root.right, id);
        } else {

            // Case 1: Node with NO child (Leaf Node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node with exactly ONE child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node with two children
            // Get the inorder successor (smallest in the right subtree)
            root.info = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.info.getId());
        }

        return root;
    }

    private Customer minValue(Node root) {
        Customer minv = root.info;
        while (root.left != null) {
            minv = root.left.info;
            root = root.left;
        }
        return minv;
    }
}
