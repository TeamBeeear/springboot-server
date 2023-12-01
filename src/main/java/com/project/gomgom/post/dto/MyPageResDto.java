package com.project.gomgom.post.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyPageResDto {
    @ApiModelProperty(value = "게시글 기본키", example = "1")
    private Long postId;
    @ApiModelProperty(value = "게시판 기본키", example = "1")
    private Long boardId;
    @ApiModelProperty(value = "게시글 제목", example = "게시글 제목입니다.")
    private String title;
    @ApiModelProperty(value = "댓글 개수", example = "3")
    private Long commentsCount;
    @ApiModelProperty(value = "좋아요 개수", example = "4")
    private Long heartsCount;
    @ApiModelProperty(value = "첫 번째 선택지 내용", example = "여름")
    private String firstSelectionContent;
    @ApiModelProperty(value = "두 번째 선택지 내용", example = "겨울")
    private String secondSelectionContent;
    @ApiModelProperty(value = "게시글 내용", example = "게시글 내용입니다.")
    private String content;
    @ApiModelProperty(value = "사용자 아이디", example = "test1")
    private String userId;
    @ApiModelProperty(value = "게시글 생성 일시", example = "1분 전")
    private String nMinutesAgo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPageResDto that = (MyPageResDto) o;
        return Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

}
