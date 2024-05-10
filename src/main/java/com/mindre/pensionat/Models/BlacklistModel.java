package com.mindre.pensionat.Models;

import lombok.Data;
//NO USAGE FOR THIS CLASS ATM
@Data
public class BlacklistModel {
    private int id;
    private String email;
    private String name;
    private String group;
    private String created;
    private boolean ok;
}
