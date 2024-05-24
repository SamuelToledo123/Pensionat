package com.mindre.pensionat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDataSeeder {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public void Seed(){
        if (roleRepository.findByName("Admin") == null) {
            addRole("Admin");
        }
        if (roleRepository.findByName("Customer") == null) {
            addRole("Customer");
        }
        if(userRepository.getUserByUsername("kareem.drammeh@yh.nackademin.se") == null){
            addUser("kareem.drammeh@yh.nackademin.se","Admin");
        }
        if(userRepository.getUserByUsername("martin@outlook.com") == null){
            addUser("martin@outlook.com","Customer");
        }
    }

    private void addUser(String mail, String group) {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(group));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("Hejsan123#");
        User user = User.builder().enabled(true).password(hash).username(mail).roles(roles).build();
        userRepository.save(user);
    }

    private void addRole(String name) {
        Role role = new Role();
        roleRepository.save(Role.builder().name(name).build());
    }
}