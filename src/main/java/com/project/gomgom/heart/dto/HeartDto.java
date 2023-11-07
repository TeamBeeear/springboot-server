package com.project.gomgom.heart.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeartDto {

    @ApiModelProperty(notes = "사용자 아이디", example = "ThisIsAccount")
    private String userId;

    @ApiModelProperty(notes = "게시글 아이디", example = "1")
    private Long postId;

}
