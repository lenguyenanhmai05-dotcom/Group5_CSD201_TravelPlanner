package com.travelplanner.structures;

import com.travelplanner.entities.Customer;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // 1. Insert (Recursion)
    public void insert(Customer customer) {
        root = insertRec(root, customer);
    }

    private Node insertRec(Node root, Customer customer) {
        // Base case: if tree is empty, return new node
        if (root == null) {
            root = new Node(customer);
            return root;
        }

        // Recursion down the tree
        // Compare IDs: if customer ID < root ID -> goes Left
        if (customer.compareTo(root.info) < 0) {
            root.left = insertRec(root.left, customer);
        }
        // if customer ID > root ID -> goes Right
        else if (customer.compareTo(root.info) > 0) {
            root.right = insertRec(root.right, customer);
        }

        return root;
    }

    // 2. Search (Recursion)
    public Customer search(String id) {
        // Create a dummy customer object just to use the ID for comparison
        // or we can manually compare IDs.
        // For strictness, let's manually compare in the helper or use a dummy.
        // A helper method that takes String ID is often cleaner.
        Node result = searchRec(root, id);
        if (result != null) {
            return result.info;
        }
        return null;
    }

    private Node searchRec(Node root, String id) {
        // Base Cases: root is null or key is present at root
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

    // 5. Delete (Recursion)
    public void delete(String id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, String id) {
        // Base case: If tree is empty
        if (root == null) {
            return root;
        }

        // Recurse down the tree
        if (id.compareTo(root.info.getId()) < 0) {
            root.left = deleteRec(root.left, id);
        } else if (id.compareTo(root.info.getId()) > 0) {
            root.right = deleteRec(root.right, id);
        } else {
            // Found the node to be deleted

            // Case 1 & 2: Node with only one child or no child
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
