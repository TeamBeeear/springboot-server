package com.project.gomgom.comment.service;

import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.comment.dto.CommentReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.repository.CommentRepository;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.user.repository.UserRepository;
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(CommentReqDto commentReqDto) {

        // post 가 존재하지 않는 경우
        if (!postRepository.findById(commentReqDto.getPostId()).isPresent()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // user 가 존재하지 않는 경우
        if (!userRepository.findById(commentReqDto.getUserId()).isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // comment 가 없거나 비어있는 경우
        if (commentReqDto.getContent() == null
                || commentReqDto.getContent().isEmpty()
                || commentReqDto.getContent().isBlank()) {
            throw new CustomException(ErrorCode.BAD_COMMENT_CONTENT);
        }

        // comment 생성
        Comment newComment = Comment.builder()
                .content(commentReqDto.getContent())
                .post(postRepository.findById(commentReqDto.getPostId()).get())
                .user(userRepository.findById(commentReqDto.getUserId()).get())
        .build();

        // comment 저장
        commentRepository.save(newComment);

        return newComment;

    }

    @Override
    public Collection<CommentResDto> readComments(Long boardId, Long postId) {

        // board 가 존재하지 않는 경우
        if (!boardRepository.findById(boardId).isPresent()) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        // post 가 존재하지 않는 경우
        if (!postRepository.findById(postId).isPresent()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // post 찾기
        Post gotPost = postRepository.findById(postId).get();

        // post 가 board 에 속해있는지 검증
        if (gotPost.getBoard().getBoardId() != boardId) {
            throw new CustomException(ErrorCode.NOT_MATCH_POST_BOARD);
        }

        List<Comment> comments = gotPost.getComments();
        List<CommentResDto> result = new ArrayList<>();
        for(Comment comment: comments) {
            result.add(CommentResDto.builder()
                    .userId(comment.getUser().getUserId())
                    .content(comment.getContent())
                    .beforeNMinutes("N 분 전")
                    .build());
        }

        return result;
    }

}