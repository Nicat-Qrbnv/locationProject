package com.example.locationproject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

}
