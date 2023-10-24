package com.project.gomgom.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private Long boardId;
    private String firstSelectionContent;
    private String secondSelectionContent;
}
