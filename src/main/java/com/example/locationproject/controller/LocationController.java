package com.example.locationproject.controller;

import com.example.locationproject.dto.location.LocationRequest;
import com.example.locationproject.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public void createLocation(@RequestBody LocationRequest locationRequest) {
        locationService.createLocation(locationRequest);
    }

}
