package com.example.locationproject.controller;

import com.example.locationproject.dto.RequestDto;
import com.example.locationproject.dto.ResponseDto;
import com.example.locationproject.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/markers")
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/new")
    public ResponseDto createLocation(@RequestBody RequestDto requestDto) {
        return locationService.createMarker(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseDto getLocation(@PathVariable Long id) {
        return locationService.getMarker(id);
    }

    @GetMapping("/all")
    public List<ResponseDto> getAllLocations() {
        return locationService.getAllMarkers();
    }

    @PutMapping("/{id}")
    public ResponseDto updateLocation(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return locationService.updateMarker(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteLocation(@PathVariable Long id) {
        return locationService.deleteMarker(id);
    }
}
