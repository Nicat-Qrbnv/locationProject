package com.example.locationproject.repository;

import com.example.locationproject.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<Marker, Long> {
}