package com.tolgaozgun.meettime.dto;


import com.tolgaozgun.meettime.entity.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private UserRole role;
}
