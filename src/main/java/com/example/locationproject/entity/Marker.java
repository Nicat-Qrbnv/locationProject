package com.example.locationproject.entity;

import com.example.locationproject.enums.MarkerType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "markers")
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    private String description;
    private MarkerType markerType;
    private double latitude;
    private double longitude;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
