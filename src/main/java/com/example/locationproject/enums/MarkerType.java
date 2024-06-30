package com.example.locationproject.enums;

public enum MarkerType {

    CUSTOM,
    BUILDING,
    ROAD,
    RESTAURANT,
    STORE,
    PARK,
    RIVER,
    LAKE;


    @Override
    public String toString() {
        return this.name();
    }
}
