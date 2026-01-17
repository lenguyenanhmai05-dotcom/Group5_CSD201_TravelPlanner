package main;

import linkedlist.*;
public class Main {
    public static void main(String[] args) {
        TourLinkedList tour = new TourLinkedList();

        tour.addFirst(new Location("Paris", "France"));
        tour.addFirst(new Location("Rome", "Italy"));

        System.out.println("AddFirst runs OK, no NullPointerException");
    }
}

