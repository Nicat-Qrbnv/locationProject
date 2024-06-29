package com.example.locationproject.controller;

import com.example.locationproject.dto.RequestDto;
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
    public void createLocation(@RequestBody RequestDto requestDto) {
        locationService.createLocation(requestDto);
    }

    @GetMapping("/{id}")
    public RequestDto getLocation(@PathVariable Long id) {
        return locationService.getLocation(id);
    }

    @GetMapping
    public List<RequestDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{id}")
    public void updateLocation(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        locationService.updateLocation(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

}
