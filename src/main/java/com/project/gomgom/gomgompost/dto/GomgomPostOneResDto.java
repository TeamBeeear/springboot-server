package com.project.gomgom.gomgompost.dto;

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
public class GomgomPostOneResDto {

    @ApiModelProperty(value = "곰곰이 게시글 제목", example = "곰곰이 게시글 제목입니다.")
    private String title;
    @ApiModelProperty(value = "곰곰이 게시글 내용", example = "곰곰이 게시글 내용입니다.")
    private String content;

    @JsonIgnore
    private Collection<Comment> comments;

}
