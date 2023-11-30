package com.project.gomgom.gomgompost.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GomgomPostAllResDto {
    @ApiModelProperty(notes = "곰곰이 게시글 기본키", example = "1")
    private Long id;
    @ApiModelProperty(notes = "곰곰이 게시글 제목", example = "곰곰이 게시글 제목입니다.")
    private String title;
}
