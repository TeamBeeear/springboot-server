package com.project.gomgom.comment.controller;

import com.project.gomgom.comment.dto.CommentGomgomReqDto;
import com.project.gomgom.comment.dto.CommentUserReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("user")
    public ResponseEntity<?> createUserPostComment(@RequestBody CommentUserReqDto dto) {
        Comment result = commentService.createUserPostComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 정상적으로 등록되었습니다.");
    }

    @PostMapping("gomgom")
    public ResponseEntity<?> createGomgomPostComment(@RequestBody CommentGomgomReqDto dto) {
        Comment result = commentService.createGomgomPostComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 정상적으로 등록되었습니다.");
    }


    @GetMapping("{boardId}/{postId}")
    public ResponseEntity<?> readUserPostComments(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId) {
        Collection<CommentResDto> result = commentService.readUserPostComments(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("{gomgomPostId}")
    public ResponseEntity<?> readGomgomPostComments(@PathVariable("gomgomPostId") Long gomgomPostId) {
        Collection<CommentResDto> result = commentService.readGomgomPostComments(gomgomPostId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}