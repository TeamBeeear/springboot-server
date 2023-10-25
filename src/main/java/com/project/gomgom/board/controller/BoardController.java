package com.project.gomgom.board.controller;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping
    public ResponseEntity<String> createBoard(@RequestBody BoardDto boardDto) {
        BoardDto result = boardServiceImpl.createBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body("게시판 생성");
    }

    @GetMapping
    public ResponseEntity<Collection<BoardDto>> readBoardAll() {
        return ResponseEntity.status(HttpStatus.OK).body(boardServiceImpl.readAllBoard());
    }

}