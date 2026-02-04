package com.travelplanner.entities;

public class Customer implements Comparable<Customer> {
    private String id;
    private String name;
    private String phone;

    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int compareTo(Customer other) {
        // Compare by ID for BST sorting
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "Customer[ID=" + id + ", Name=" + name + "]";
    }
}
