package com.travelplanner.app;

import com.travelplanner.entities.TourLocation;
import com.travelplanner.structures.TourLinkedList;

public class App {
    public static void main(String[] args) {
        TourLinkedList tour = new TourLinkedList();

        TourLocation gl = new TourLocation("GL", "Gia Lai", "Pho Nui Pleiku", 0.0);
        TourLocation kt = new TourLocation("KT", "Kon Tum", "Mang Den", 150.0);
        TourLocation qn = new TourLocation("QN", "Quy Nhon", "Thanh pho bien", 200.0);
        TourLocation py = new TourLocation("PY", "Phu Yen", "Hoa vang tren co xanh", 300.0);

        System.out.println("--- LINKED LIST ---");

        // 1. Thêm cuối
        tour.addLast(gl);
        tour.addLast(qn);
        System.out.println("1. Ban dau: " + tour.print());

        // 2. Thêm đầu
        tour.addFirst(kt);
        System.out.println("2. Them Kon Tum vao dau: " + tour.print());

        // 3. Chèn giữa
        tour.insertAfter("QN", py);
        System.out.println("3. Chen Phu Yen sau Quy Nhon: " + tour.print());

        // 4. Xoá
        tour.removeById("GL");
        System.out.println("4. Xoa Gia Lai: " + tour.print());
    }
}