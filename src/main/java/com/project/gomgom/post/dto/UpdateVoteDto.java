package com.project.gomgom.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateVoteDto {
    private String updatedFirstVotePercentage;
    private String updatedSecondVotePercentage;
}
