package com.mindre.pensionat.Dtos;

import com.mindre.pensionat.security.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String username;
    private String password;
    private Collection<String> roles;
    RoleDto roleDto;

}

