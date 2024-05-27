package com.mindre.pensionat.Utils;

import com.mindre.pensionat.Dtos.UserDto;
import com.mindre.pensionat.security.User;
import com.mindre.pensionat.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }


        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
    }

    public static User toUserDto(UserDto userDto, PasswordEncoder passwordEncoder) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .enabled(true)
                .build();
    }
}