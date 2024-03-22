package com.tolgaozgun.meettime.mapper;

import com.tolgaozgun.meettime.dto.UserDto;
import com.tolgaozgun.meettime.entity.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {
    public static UserDto toDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .picture(user.getPicture())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    public static List<UserDto> toDTO(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(toDTO(user));
        }

        return userDtos;
    }
}
