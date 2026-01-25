package com.travelplanner.app;

import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.TourLinkedList;

public class App {
    public static void main(String[] args) {
        TourLinkedList tour = new TourLinkedList();

        TourLocation hcm = new TourLocation("HCM", "Ho Chi Minh", "Desc", 0.0);
        TourLocation hue = new TourLocation("HUE", "Hue", "Desc", 300.0);
        TourLocation dn = new TourLocation("DN", "Da Nang", "Desc", 500.0);
        TourLocation ha = new TourLocation("HA", "Hoi An", "Desc", 400.0);

        System.out.println("--- LINKED LIST ---");

        // 1. Thêm cuối
        tour.addLast(hcm);
        tour.addLast(dn);
        System.out.println("1. Ban dau: " + tour.print());

        // 2. Thêm đầu
        tour.addFirst(hue);
        System.out.println("2. Them Hue vao dau: " + tour.print());

        // 3. Chèn giữa
        tour.insertAfter("DN", ha);
        System.out.println("3. Chen Hoi An sau Da Nang: " + tour.print());

        // 4. Xoá
        tour.removeById("HCM");
        System.out.println("4. Xoa HCM: " + tour.print());
    }
}