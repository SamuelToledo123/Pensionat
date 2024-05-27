package com.mindre.pensionat.security;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="Role")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    public Role(String name) {
        this.name = name;
    }
}


