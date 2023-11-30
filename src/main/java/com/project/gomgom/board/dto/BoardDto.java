package com.project.gomgom.board.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    @ApiModelProperty(notes = "게시판 기본키", example = "1")
    private Long boardId;
    @ApiModelProperty(notes = "게시판 이름", example = "대인관계")
    private String boardName;
}