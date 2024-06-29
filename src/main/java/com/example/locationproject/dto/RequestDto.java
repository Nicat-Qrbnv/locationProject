package com.example.locationproject.dto;

import lombok.Data;

@Data
public class RequestDto {
    private String title;
    private String description;
    private int markerOrdinal;
    private double latitude;
    private double longitude;
}