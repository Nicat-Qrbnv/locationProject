package com.example.locationproject.dto.location;

import com.example.locationproject.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LocationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String title;
    private String description;
    private LocationType locationType;
    private double latitude;
    private double longitude;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
