package com.project.gomgom.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    @ApiModelProperty(notes = "사용자 아이디", example = "ThisIsAccount")
    private String userId;

    @ApiModelProperty(notes = "사용자 비밀번호", example = "ThisIsPw1234")
    private String userPw;

    public UserDto passwordMasked() {
        return new UserDtoBuilder()
                .userId(this.userId)
                .userPw("****")
                .build();
    }
}