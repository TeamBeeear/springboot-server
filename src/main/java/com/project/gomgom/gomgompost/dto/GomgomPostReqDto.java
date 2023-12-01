package com.project.gomgom.gomgompost.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GomgomPostReqDto {
    @ApiModelProperty(value = "곰곰이 게시글 제목", example = "곰곰이 게시글 제목입니다.")
    private String title;
    @ApiModelProperty(value = "곰곰이 게시글 내용", example = "곰곰이 게시글 내용입니다.")
    private String content;
}
