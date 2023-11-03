package com.project.gomgom.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CommentResDto {
    private String userId;
    private String content;
    private String nMinutesAgo;
}
