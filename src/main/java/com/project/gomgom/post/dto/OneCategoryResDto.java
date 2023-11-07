package com.project.gomgom.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class OneCategoryResDto {

    private Long postId;
    private Long boardId;
    private String userId;
    private String title;
    private String content;
    private Long firstSelectionId;
    private String firstSelectionContent;
    private String firstSelectionVotePercentage;
    private Long secondSelectionId;
    private String secondSelectionContent;
    private String secondSelectionVotePercentage;
    private Long commentsCount;
    private Long heartsCount;

}
