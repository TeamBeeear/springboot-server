package com.project.gomgom.comment.controller;

import com.project.gomgom.comment.dto.CommentReqDto;
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

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentReqDto commentReqDto) {
        Comment result = commentService.createComment(commentReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 정상적으로 등록되었습니다.");
    }

    @GetMapping("{postId}")
    public ResponseEntity<?> readComments(@PathVariable("postId") Long postId) {
        Collection<CommentResDto> result = commentService.readComments(postId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}