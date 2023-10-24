package com.project.gomgom.board.service;

import com.project.gomgom.board.dto.BoardDto;
import com.project.gomgom.board.entity.Board;
import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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

    // 모든 게시글 보여주기
    public List<Post> readAllPost() {
        return null;
    }

    // 게시판에 해당하는 모든 글 보여주기
    public List<Post> readBoardPost() {
        return null;
    }

}
