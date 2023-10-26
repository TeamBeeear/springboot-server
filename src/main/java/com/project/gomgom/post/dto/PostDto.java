package com.project.gomgom.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private String title;

    private String content;

    @ApiModelProperty(example = "test1")
    private String userId;

    @ApiModelProperty(example = "1")
    private Long boardId;

    @ApiModelProperty(example = "첫번째 선택지 내용")
    private String firstSelectionContent;

    @ApiModelProperty(example = "두번째 선택지 내용")
    private String secondSelectionContent;

}
