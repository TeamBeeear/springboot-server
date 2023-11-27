package com.project.gomgom.board.controller;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.service.BoardServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Api(tags = "게시판")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("board")
    @ApiOperation(value = "게시판 생성", notes = "관리자가 게시판을 생성할 때 호출합니다.")
    public ResponseEntity<?> createBoard(@RequestBody BoardDto boardDto) {

        try {
            BoardDto result = boardServiceImpl.createBoard(boardDto);
            return ResponseEntity.status(HttpStatus.OK).body("게시판 생성");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("board")
    @ApiOperation(value = "게시판 조회", notes = "생성된 게시판의 이름을 조회할 때 호출합니다.")
    public ResponseEntity<?> readBoardAll() {

        try {
            Collection<BoardDto> result = boardServiceImpl.readAllBoard();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}