package com.project.gomgom.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AllCategoryResDto {

    private Long postId;
    private Long boardId;
    private String title;
    private Long commentsCount;
    private Long heartsCount;
    private String firstSelectionContent;
    private String secondSelectionContent;
    private String content;
    private String userId;
    private String nMinutesAgo;

}
