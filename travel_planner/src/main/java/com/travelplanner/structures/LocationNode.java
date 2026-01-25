package com.travelplanner.structures;

import com.travelplanner.entities.TourLocation;

public class LocationNode {
    public TourLocation info;
    public LocationNode next;

    public LocationNode(TourLocation info) {
        this.info = info;
        this.next = null;
    }
}