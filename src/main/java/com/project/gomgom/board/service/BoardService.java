package com.project.gomgom.board.service;

import com.project.gomgom.board.dto.BoardDto;
import java.util.Collection;

public interface BoardService {
    BoardDto createBoard(BoardDto boardDto);
    Collection<BoardDto> readAllBoard();
}
