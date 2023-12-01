package com.project.gomgom.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.comment.entity.Comment;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OnePostResDto {
    @ApiModelProperty(value = "게시글 기본키", example = "1")
    private Long postId;
    @ApiModelProperty(value = "게시판 기본키", example = "1")
    private Long boardId;
    @ApiModelProperty(value = "사용자 아이디", example = "test1")
    private String userId;
    @ApiModelProperty(value = "게시글 내용", example = "게시글 내용입니다.")
    private String title;
    @ApiModelProperty(value = "게시글 내용", example = "게시글 내용입니다.")
    private String content;
    @ApiModelProperty(value = "첫 번째 선택지 기본키", example = "1")
    private Long firstSelectionId;
    @ApiModelProperty(value = "첫 번째 선택지 내용", example = "여름")
    private String firstSelectionContent;
    @ApiModelProperty(value = "첫 번째 선택지 투표율", example = "30%")
    private String firstSelectionVotePercentage;
    @ApiModelProperty(value = "두 번째 선택지 기본키", example = "2")
    private Long secondSelectionId;
    @ApiModelProperty(value = "두 번째 선택지 내용", example = "겨울")
    private String secondSelectionContent;
    @ApiModelProperty(value = "두 번째 선택지 투표율", example = "70%")
    private String secondSelectionVotePercentage;

    @JsonIgnore
    private Collection<Comment> comments;

    @ApiModelProperty(value = "댓글 개수", example = "3")
    private Long commentsCount;
    @ApiModelProperty(value = "좋아요 개수", example = "4")
    private Long heartsCount;
}
