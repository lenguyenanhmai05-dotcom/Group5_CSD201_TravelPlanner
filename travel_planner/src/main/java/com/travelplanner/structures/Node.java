package com.travelplanner.structures;

import com.travelplanner.entities.Customer;

public class Node {
    public Customer info;
    public Node left;
    public Node right;

    public Node(Customer info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }
}
