package com.example.locationproject.dto;

import com.example.locationproject.enums.MarkerType;
import lombok.Data;

@Data
public class ResponseDto {
    private Long id;
    private String title;
    private String description;
    private MarkerType markerType;
    private double latitude;
    private double longitude;
}