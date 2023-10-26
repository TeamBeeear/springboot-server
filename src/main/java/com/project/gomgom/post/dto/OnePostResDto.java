package com.project.gomgom.post.dto;

import com.project.gomgom.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Builder
public class OnePostResDto {

    private Long postId;
    private String userId;
    private String title;
    private String content;
    private Long firstSelectionId;
    private String firstSelectionContent;
    private String firstSelectionVotePercentage;
    private Long secondSelectionId;
    private String secondSelectionContent;
    private String secondSelectionVotePercentage;
    private Collection<Comment> comments;
    private Long commentsCount;
    private Long heartsCount;
}
