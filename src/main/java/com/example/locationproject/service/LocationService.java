package com.example.locationproject.service;

import com.example.locationproject.dto.RequestDto;
import com.example.locationproject.dto.ResponseDto;
import com.example.locationproject.util.Mappable;
import com.example.locationproject.entity.Marker;
import com.example.locationproject.exception.ResourceNotFoundException;
import com.example.locationproject.repository.MarkerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final MarkerRepository markerRepo;
    private final ModelMapper mapper;

    public ResponseDto createMarker(RequestDto requestDto) {
        Marker marker = mapper.map(requestDto, Marker.class);
        marker = markerRepo.save(marker);
        return mapper.map(marker, ResponseDto.class);
    }

    public ResponseDto getMarker(Long id) {
        Marker marker = markerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return mapper.map(marker, ResponseDto.class);
    }

    public List<ResponseDto> getAllMarkers() {
        List<Marker> markers = markerRepo.findAll();
        return listMapping(markers, ResponseDto.class);
    }

    public ResponseDto updateMarker(Long id, RequestDto requestDto) {
        Marker existingMarker = markerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        mapper.map(requestDto, existingMarker);
        markerRepo.save(existingMarker);
        return mapper.map(existingMarker, ResponseDto.class);
    }

    public ResponseDto deleteMarker(Long id) {
        Marker marker = markerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        markerRepo.delete(marker);
        return mapper.map(marker, ResponseDto.class);
    }

    public <D, S> List<D> listMapping(List<S> source, Class<D> destination) {
        return source.stream().map(s -> mapper.map(s, destination)).toList();
    }
}
