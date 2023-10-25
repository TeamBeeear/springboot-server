package com.project.gomgom.board.service;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.entity.Board;
import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    // 게시판 생성
    @Override
    public BoardDto createBoard(BoardDto boardDto) {

        Board newBoard = Board.builder()
                .boardName(boardDto.getBoardName())
                .build();

        Board board = boardRepository.save(newBoard);
        return boardDto;
    }

    // 모든 게시판 보여주기
    @Override
    public Collection<BoardDto> readAllBoard() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        boardRepository.findAll().forEach((board) -> boardDtoList.add(new BoardDto(
                board.getBoardName()
        )));

        return boardDtoList;
    }

}
