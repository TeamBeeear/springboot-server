package com.project.gomgom.comment.controller;

import com.project.gomgom.comment.dto.CommentGomgomReqDto;
import com.project.gomgom.comment.dto.CommentUserReqDto;
import com.project.gomgom.comment.dto.CommentResDto;
import com.project.gomgom.comment.entity.Comment;
import com.project.gomgom.comment.service.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
@Api(tags = "댓글")
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("user")
    @ApiOperation(value = "사용자 게시글에 댓글 작성", notes = "사용자가 사용자의 게시글에 댓글을 작성할 때 호출합니다.")
    public ResponseEntity<?> createUserPostComment(@RequestBody CommentUserReqDto dto) {
        Comment result = commentService.createUserPostComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 정상적으로 등록되었습니다.");
    }

    @PostMapping("gomgom")
    @ApiOperation(value = "곰곰이 게시글에 댓글 작성", notes = "사용자가 곰곰이의 게시글에 댓글을 작성할 때 호출합니다.")
    public ResponseEntity<?> createGomgomPostComment(@RequestBody CommentGomgomReqDto dto) {
        Comment result = commentService.createGomgomPostComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 정상적으로 등록되었습니다.");
    }

    @GetMapping("{boardId}/{postId}")
    @ApiOperation(value = "사용자 게시글의 댓글 조회", notes = "사용자의 게시글에 등록된 댓글을 볼 때 호출합니다.")
    public ResponseEntity<?> readUserPostComments(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId) {
        Collection<CommentResDto> result = commentService.readUserPostComments(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("{gomgomPostId}")
    @ApiOperation(value = "곰곰이 게시글의 댓글 조회", notes = "곰곰이 게시글에 등록된 댓글을 볼 때 호출합니다.")
    public ResponseEntity<?> readGomgomPostComments(@PathVariable("gomgomPostId") Long gomgomPostId) {
        Collection<CommentResDto> result = commentService.readGomgomPostComments(gomgomPostId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}