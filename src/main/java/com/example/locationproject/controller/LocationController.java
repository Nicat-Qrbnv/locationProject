package com.example.locationproject.controller;

import com.example.locationproject.dto.location.LocationRequest;
import com.example.locationproject.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public void createLocation(@RequestBody LocationRequest locationRequest) {
        locationService.createLocation(locationRequest);
    }

    @GetMapping("/{id}")
    public LocationRequest getLocation(@PathVariable Long id) {
        return locationService.getLocation(id);
    }

    @GetMapping
    public List<LocationRequest> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{id}")
    public void updateLocation(@PathVariable Long id, @RequestBody LocationRequest locationRequest) {
        locationService.updateLocation(id, locationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

}
