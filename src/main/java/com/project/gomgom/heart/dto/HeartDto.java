package com.project.gomgom.heart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeartDto {
    private String userId;
    private Long postId;
}
