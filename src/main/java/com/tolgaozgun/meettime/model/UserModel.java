package com.tolgaozgun.meettime.model;

import lombok.Data;

@Data
public class UserModel {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;

}
