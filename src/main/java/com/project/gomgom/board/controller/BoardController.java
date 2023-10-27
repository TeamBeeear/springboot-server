package com.project.gomgom.board.controller;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.entity.Board;
import com.project.gomgom.board.service.BoardServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Api(value = "Board Controller")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("board")
    public ResponseEntity<?> createBoard(@RequestBody BoardDto boardDto) {

        try {
            BoardDto result = boardServiceImpl.createBoard(boardDto);
            return ResponseEntity.status(HttpStatus.OK).body("게시판 생성");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("board")
    public ResponseEntity<?> readBoardAll() {

        try {
            Collection<BoardDto> result = boardServiceImpl.readAllBoard();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}