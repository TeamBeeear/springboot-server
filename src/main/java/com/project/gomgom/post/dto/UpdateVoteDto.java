package com.project.gomgom.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateVoteDto {
    @ApiModelProperty(value = "업데이트 된 첫 번째 선택지 투표율", example = "40%")
    private String updatedFirstVotePercentage;
    @ApiModelProperty(value = "업데이트 된 두 번째 선택지 투표율", example = "60%")
    private String updatedSecondVotePercentage;
}
