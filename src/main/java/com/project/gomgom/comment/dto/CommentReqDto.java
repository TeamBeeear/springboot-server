package com.project.gomgom.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentReqDto {
    private Long postId;
    private String userId;
    private String content;
}