package com.project.gomgom.board.service;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.entity.Board;
import com.project.gomgom.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 생성
    public BoardDto createBoard(BoardDto boardDto) {

        Board newBoard = Board.builder()
                .boardName(boardDto.getBoardName())
                .build();

        Board board = boardRepository.save(newBoard);
        return boardDto;
    }

}
