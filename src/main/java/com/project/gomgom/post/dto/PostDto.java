package com.project.gomgom.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private String title;
    private String content;
    private String userId;
    private Long boardId;
    private String firstSelectionContent;
    private String secondSelectionContent;

}
