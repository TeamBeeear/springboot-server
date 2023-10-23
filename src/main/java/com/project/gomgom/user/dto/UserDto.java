package com.project.gomgom.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private String userId;
    private String userPw;

    public UserDto passwordMasked() {
        return new UserDtoBuilder()
                .userId(this.userId)
                .userPw("****")
                .build();
    }
}
