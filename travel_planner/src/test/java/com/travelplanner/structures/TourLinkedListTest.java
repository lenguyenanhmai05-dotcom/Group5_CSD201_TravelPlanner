package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TourLinkedListTest {

    private TourLocation createLoc(String id, String name) {
        return new TourLocation(id, name, "Description", 100.0);
    }
     @Test
    public void testAddLast() {
        TourLinkedList list = new TourLinkedList();
        list.addLast(createLoc("1", "A"));
        list.addLast(createLoc("2", "B"));
        assertEquals("A -> B -> END", list.print());
    }
     @Test
    public void testAddFirst() {
        TourLinkedList list = new TourLinkedList();
        list.addLast(createLoc("1", "A"));
        list.addFirst(createLoc("2", "B")); // Thêm B vào trước A
        assertEquals("B -> A -> END", list.print());
    }
}



   
   