package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TourLinkedListTest {

    private TourLocation createLoc(String id, String name) {
        return new TourLocation(id, name, "Description", 100.0);
    }
}

   
   