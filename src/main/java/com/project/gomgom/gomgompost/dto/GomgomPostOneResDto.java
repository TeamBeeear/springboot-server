package com.project.gomgom.gomgompost.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.gomgom.comment.entity.Comment;
import java.util.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GomgomPostOneResDto {

    private String title;
    private String content;

    @JsonIgnore
    private Collection<Comment> comments;

}
