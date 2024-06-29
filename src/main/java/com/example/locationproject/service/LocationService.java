package com.example.locationproject.service;

import com.example.locationproject.dto.RequestDto;
import com.example.locationproject.entity.Marker;
import com.example.locationproject.exception.ResourceNotFoundException;
import com.example.locationproject.repository.MarkerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final MarkerRepository markerRepo;
    private final ModelMapper mapper;

    public void createMarker(RequestDto requestDto) {
        Marker marker = mapper.map(requestDto, Marker.class);
        markerRepo.save(marker);
    }

    public RequestDto getLocation(Long id) {
        Marker marker = markerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return mapper.map(marker, RequestDto.class);
    }

    public List<RequestDto> getAllLocations() {
        List<Marker> markers = markerRepo.findAll();
        return markers.stream()
                .map(marker -> mapper.map(marker, RequestDto.class))
                .collect(Collectors.toList());
    }

    public void updateLocation(Long id, RequestDto requestDto) {
        Marker existingMarker = markerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        mapper.map(requestDto, existingMarker);
        markerRepo.save(existingMarker);
    }

    public void deleteLocation(Long id) {
        Marker existingMarker = markerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        markerRepo.delete(existingMarker);
    }
}
