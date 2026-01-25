package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;

public class TourLinkedList {

    private LocationNode head;

    public TourLinkedList() {
        head = null;
    }

    // 1. Thêm vào cuối (Add Last)
    public void addLast(TourLocation item) {
        LocationNode node = new LocationNode(item);
        if (head == null) {
            head = node;
            return;
        }
        LocationNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    // 2. Thêm vào đầu (Add First) - MỚI
    public void addFirst(TourLocation item) {
        LocationNode newNode = new LocationNode(item);
        newNode.next = head;
        head = newNode;
    }
}