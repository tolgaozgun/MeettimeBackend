package com.tolgaozgun.meettime.dto;


import com.tolgaozgun.meettime.entity.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private UserRole role;

    @NotNull
    private String picture;

    public String toString(){
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
