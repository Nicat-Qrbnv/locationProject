package com.example.locationproject.dtos;

import com.example.locationproject.enums.MarkerType;
import lombok.Data;

@Data
public class RequestDto {
    private String title;
    private String description;
    private MarkerType markerType;
    private double latitude;
    private double longitude;
}