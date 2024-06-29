package com.example.locationproject.service;

import com.example.locationproject.dto.location.LocationRequest;
import com.example.locationproject.entity.Location;
import com.example.locationproject.exception.ResourceNotFoundException;
import com.example.locationproject.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createLocation(LocationRequest locationRequest) {
        Location location = modelMapper.map(locationRequest, Location.class);
        locationRepository.save(location);
    }

    public LocationRequest getLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return modelMapper.map(location, LocationRequest.class);
    }

    public List<LocationRequest> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(location -> modelMapper.map(location, LocationRequest.class))
                .collect(Collectors.toList());
    }

    public void updateLocation(Long id, LocationRequest locationRequest) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        modelMapper.map(locationRequest, existingLocation);
        locationRepository.save(existingLocation);
    }

    public void deleteLocation(Long id) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        locationRepository.delete(existingLocation);
    }
}
