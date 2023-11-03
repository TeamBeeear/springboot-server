package com.project.gomgom.gomgompost.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Builder
public class GomgomPostOneResDto {

    private String title;
    private String content;

    @JsonIgnore
    private Collection<Comment> comments;

}
