package com.mindre.pensionat.Security;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.UUID;

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    private String username;
       private String password;

       @OneToMany(fetch = FetchType.EAGER)
       private Collection<Role> roles;

}
