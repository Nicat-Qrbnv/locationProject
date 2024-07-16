package com.example.locationproject.entities;

import com.example.locationproject.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;

    private final Set<Role> authorities = new HashSet<>();
    private boolean credentialsNonExpired = true;

    public void addAuthority(Role role) {
        authorities.add(role);
    }
}