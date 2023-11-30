package com.project.gomgom.comment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CommentResDto {
    @ApiModelProperty(notes = "댓글 작성자", example = "test1")
    private String userId;
    @ApiModelProperty(notes = "댓글 내용", example = "댓글을 작성하는 예시입니다.")
    private String content;
    @ApiModelProperty(notes = "댓글 등록 시간", example = "1분 전")
    private String nMinutesAgo;
}
