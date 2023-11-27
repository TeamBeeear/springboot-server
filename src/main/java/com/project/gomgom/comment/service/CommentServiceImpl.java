package com.project.gomgom.comment.service;

import com.project.gomgom.board.repository.BoardRepository;
import com.project.gomgom.comment.dto.CommentGomgomReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.dto.CommentUserReqDto;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.repository.CommentRepository;
import com.project.gomgom.gomgompost.entity.GomgomPost;
import com.project.gomgom.gomgompost.repository.GomgomPostRepository;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.user.repository.UserRepository;
import com.project.gomgom.util.exception.CustomException;
import com.project.gomgom.util.exception.ErrorCode;
import com.project.gomgom.util.formatter.TimeAgoFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final GomgomPostRepository gomgomPostRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment createUserPostComment(CommentUserReqDto commentUserReqDto) {

        // post 가 존재하지 않는 경우
        if (!postRepository.findById(commentUserReqDto.getPostId()).isPresent()) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // user 가 존재하지 않는 경우
        if (!userRepository.findById(commentUserReqDto.getUserId()).isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // comment 가 없거나 비어있는 경우
        if (commentUserReqDto.getContent() == null
                || commentUserReqDto.getContent().isEmpty()
                || commentUserReqDto.getContent().isBlank()) {
            throw new CustomException(ErrorCode.BAD_COMMENT_CONTENT);
        }

        // comment 생성
        Comment newComment = Comment.builder()
                .content(commentUserReqDto.getContent())
                .post(postRepository.findById(commentUserReqDto.getPostId()).get())
                .user(userRepository.findById(commentUserReqDto.getUserId()).get())
                .build();

        // comment 저장
        commentRepository.save(newComment);

        return newComment;

    }

    @Override
    public Comment createGomgomPostComment(CommentGomgomReqDto dto) {

        // gomgom post 가 존재하지 않는 경우
        if (!gomgomPostRepository.findById(dto.getGomgomPostId()).isPresent()) {
            throw new CustomException(ErrorCode.GOMGOM_POST_NOT_FOUND);
        }

        // user 가 존재하지 않는 경우
        if (!userRepository.findById(dto.getUserId()).isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // comment 가 없거나 비어있는 경우
        if (dto.getContent() == null
                || dto.getContent().isEmpty()
                || dto.getContent().isBlank()) {
            throw new CustomException(ErrorCode.BAD_COMMENT_CONTENT);
        }

        // comment 생성
        Comment newComment = Comment.builder()
                .content(dto.getContent())
                .gomgomPost(gomgomPostRepository.findById(dto.getGomgomPostId()).get())
                .user(userRepository.findById(dto.getUserId()).get())
                .build();

        // comment 저장
        commentRepository.save(newComment);

        return newComment;

    }

    @Override
    public Collection<CommentResDto> readUserPostComments(Long boardId, Long postId) {

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
                            .nMinutesAgo(TimeAgoFormatter.format(comment.getCreatedAt()))
                    .build());
        }

        return result;
    }

    @Override
    public Collection<CommentResDto> readGomgomPostComments(Long gomgomPostId) {

        // gomgom post 가 존재하지 않는 경우
        if (!gomgomPostRepository.findById(gomgomPostId).isPresent()) {
            throw new CustomException(ErrorCode.GOMGOM_POST_NOT_FOUND);
        }

        // post 찾기
        GomgomPost gotPost = gomgomPostRepository.findById(gomgomPostId).get();

        List<Comment> comments = gotPost.getComments();
        List<CommentResDto> result = new ArrayList<>();
        for(Comment comment: comments) {
            result.add(CommentResDto.builder()
                    .userId(comment.getUser().getUserId())
                    .content(comment.getContent())
                    .nMinutesAgo(TimeAgoFormatter.format(comment.getCreatedAt()))
                    .build());
        }

        return result;
    }

}