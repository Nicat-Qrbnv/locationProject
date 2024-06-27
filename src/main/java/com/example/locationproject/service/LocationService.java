package com.example.locationproject.service;

import com.example.locationproject.dto.location.LocationRequest;
import com.example.locationproject.dto.location.LocationResponse;
import com.example.locationproject.entity.Location;
import com.example.locationproject.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationResponse createLocation(LocationRequest locationRequest) {
        Location location = Location.builder()
                .title(locationRequest.getTitle())
                .description(locationRequest.getDescription())
                .locationType(locationRequest.getLocationType())
                .latitude(locationRequest.getLatitude())
                .longitude(locationRequest.getLongitude())
                .createdDate(locationRequest.getCreatedDate())
                .updatedDate(locationRequest.getUpdatedDate())
                .build();


        Location savedLocation = locationRepository.save(location);

        LocationResponse locationResponse = LocationResponse.builder()
                .id(savedLocation.getId())
                .title(savedLocation.getTitle())
                .description(savedLocation.getDescription())
                .locationType(savedLocation.getLocationType())
                .latitude(savedLocation.getLatitude())
                .longitude(savedLocation.getLongitude())
                .createdDate(savedLocation.getCreatedDate())
                .updatedDate(savedLocation.getUpdatedDate())
                .build();

        return locationResponse;
    }
}
