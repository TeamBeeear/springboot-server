package com.project.gomgom.board.controller;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("board")
    public ResponseEntity<String> createBoard(@RequestBody BoardDto boardDto) {
        BoardDto result = boardService.createBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body("게시판 생성");
    }

}