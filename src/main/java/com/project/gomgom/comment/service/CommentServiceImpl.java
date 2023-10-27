package com.project.gomgom.comment.service;

import com.project.gomgom.comment.dto.CommentReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.repository.CommentRepository;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.repository.PostRepository;
import com.project.gomgom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(CommentReqDto commentReqDto) {

        // post 검증
        if (!postRepository.findById(commentReqDto.getPostId()).isPresent()) {
            throw new RuntimeException("게시글이 존재하지 않습니다.");
        }

        // user 검증
        if (!userRepository.findById(commentReqDto.getUserId()).isPresent()) {
            throw new NoSuchElementException("유저가 존재하지 않습니다.");
        }

        // comment 검증
        if (commentReqDto.getContent().equals("") || commentReqDto.getContent() == null) {
            throw new NotAcceptableStatusException("댓글이 존재하지 않습니다.");
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
    public Collection<CommentResDto> readComments(Long postId) {

        if (!postRepository.findById(postId).isPresent()) {
            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
        }

        Post gotPost = postRepository.findById(postId).get();
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
