package com.example.locationproject.service;

import com.example.locationproject.dto.RequestDto;
import com.example.locationproject.entity.Marker;
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

    public void createLocation(RequestDto requestDto) {
        Marker marker = modelMapper.map(requestDto, Marker.class);
        locationRepository.save(marker);
    }

    public RequestDto getLocation(Long id) {
        Marker marker = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return modelMapper.map(marker, RequestDto.class);
    }

    public List<RequestDto> getAllLocations() {
        List<Marker> markers = locationRepository.findAll();
        return markers.stream()
                .map(marker -> modelMapper.map(marker, RequestDto.class))
                .collect(Collectors.toList());
    }

    public void updateLocation(Long id, RequestDto requestDto) {
        Marker existingMarker = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        modelMapper.map(requestDto, existingMarker);
        locationRepository.save(existingMarker);
    }

    public void deleteLocation(Long id) {
        Marker existingMarker = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        locationRepository.delete(existingMarker);
    }
}
