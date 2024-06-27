package com.example.locationproject.dto.location;

import com.example.locationproject.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LocationResponse {
    private Long id;
    private String title;
    private String description;
    private LocationType locationType;
    private double latitude;
    private double longitude;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
