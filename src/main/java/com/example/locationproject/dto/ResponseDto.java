package com.example.locationproject.dto;

import com.example.locationproject.util.Mappable;
import com.example.locationproject.enums.MarkerType;
import lombok.Data;

@Data
public class ResponseDto implements Mappable {
    private Long id;
    private String title;
    private String description;
    private MarkerType markerType;
    private double latitude;
    private double longitude;
}