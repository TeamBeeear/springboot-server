package com.project.gomgom.comment.controller;

import com.project.gomgom.comment.dto.CommentReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentReqDto commentReqDto) {

        try {
            Comment result = commentService.createComment(commentReqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 정상적으로 등록되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 존재하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("{postId}")
    public ResponseEntity<?> readComments(@PathVariable("postId") Long postId) {

        try {
            Collection<CommentResDto> result = commentService.readComments(postId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 존재하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}
