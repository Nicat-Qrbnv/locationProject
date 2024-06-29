package com.example.locationproject.dto;

import com.example.locationproject.enums.MarkerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseDto {
    private Long id;
    private String title;
    private String description;
    private MarkerType markerType;
    private double latitude;
    private double longitude;
}
