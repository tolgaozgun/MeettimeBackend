package com.tolgaozgun.meettime.response;

import com.tolgaozgun.meettime.dto.UserDTO;
import com.tolgaozgun.meettime.entity.UserEntity;
import lombok.Data;

@Data
public class ResUserToken {

    private UserDTO user;
    private String accessToken;
    private String refreshToken;

    public ResUserToken(UserDTO user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public ResUserToken(UserEntity userEntity, String accessToken, String refreshToken) {
        this.user = new UserDTO();
        this.user.setId(userEntity.getId());
        this.user.setName(userEntity.getName());
        this.user.setEmail(userEntity.getEmail());
        this.user.setRole(userEntity.getRole());
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
