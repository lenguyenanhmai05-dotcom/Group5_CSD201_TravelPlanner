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

    // 3. Chèn vào sau một địa điểm (Insert After) - MỚI
    public boolean insertAfter(String destId, TourLocation item) {
        LocationNode curr = head;
        while (curr != null) {
            if (curr.info.getId().equals(destId)) {
                LocationNode newNode = new LocationNode(item);
                newNode.next = curr.next;
                curr.next = newNode;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // 4. Xoá theo ID
    public boolean removeById(String id) {
        if (head == null)
            return false;

        if (head.info.getId().equals(id)) {
            head = head.next;
            return true;
        }

        LocationNode prev = head;
        LocationNode cur = head.next;

        while (cur != null) {
            if (cur.info.getId().equals(id)) {
                prev.next = cur.next;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    // 5. In danh sách
    public String print() {
        StringBuilder result = new StringBuilder();
        LocationNode temp = head;
        while (temp != null) {
            result.append(temp.info.getName()).append(" -> ");
            temp = temp.next;
        }
        return result.append("END").toString();
    }

    // 6. Lấy kích thước (Size)
    public int size() {
        int count = 0;
        LocationNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}