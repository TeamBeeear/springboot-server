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
public class GptReqDto {
    @ApiModelProperty(value = "질문", example = "내일 점심 뭐 먹지?")
    private String question;
}
