package com.project.gomgom.gpt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GptResDto {
    @ApiModelProperty(example = "내일은 짜장면 어때요?")
    private String answer;
}
