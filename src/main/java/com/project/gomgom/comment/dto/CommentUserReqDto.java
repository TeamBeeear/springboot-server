package com.project.gomgom.comment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentUserReqDto {
    @ApiModelProperty(notes = "사용자 게시글 기본키", example = "1")
    private Long postId;
    @ApiModelProperty(notes = "댓글 작성자", example = "test1")
    private String userId;
    @ApiModelProperty(notes = "댓글 내용", example = "사용자 게시글에 댓글을 작성하는 예시입니다.")
    private String content;
}
