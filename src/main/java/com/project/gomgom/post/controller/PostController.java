package com.project.gomgom.post.controller;

import com.project.gomgom.post.dto.AllCategoryResDto;
import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.dto.OnePostResDto;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.service.PostServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Api(value = "Post Controller")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    // 게시글 작성
    @ApiOperation(value = "", notes = "게시글 작성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 작성이 완료되었습니다."),
            @ApiResponse(code = 404, message = "게시판 || 사용자가 존재하지 않습니다."),
            @ApiResponse(code = 500, message = "서버 에러 메세지")
    })
    @PostMapping("post")
    public ResponseEntity<?> createPost(@ApiParam(value = "글 작성") @RequestBody PostDto postDto) {
        Post result = postServiceImpl.createPost(postDto);
        return ResponseEntity.status(HttpStatus.OK).body("글 작성이 완료되었습니다.");
    }

    // 게시글 하나 조회
    @ApiOperation(value = "", notes = "게시글 하나 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 조회가 완료되었습니다."),
            @ApiResponse(code = 404, message = "게시판 || 게시글이 존재하지 않습니다."),
            @ApiResponse(code = 500, message = "서버 에러 메세지")
    })
    @GetMapping("post/{boardId}/{postId}")
    public ResponseEntity<?> readPost(@ApiParam(value = "게시판 id") @PathVariable("boardId") Long boardId, @ApiParam(value = "게시글 id")@PathVariable("postId") Long postId) {
        OnePostResDto result = postServiceImpl.readPost(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시판에 따른 모든 게시글 조회
    @ApiOperation(value = "", notes = "게시판의 글 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 조회가 완료되었습니다."),
            @ApiResponse(code = 404, message = "게시판이 존재하지 않습니다."),
            @ApiResponse(code = 500, message = "서버 에러 메세지")
    })
    @GetMapping("board/{boardId}")
    public ResponseEntity<?> readCategoryPost(@ApiParam(value = "게시판 id") @PathVariable("boardId") Long boardId) {
        Collection<OneCategoryResDto> result = postServiceImpl.readCategoryPost(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 모든 게시글 조회
    @ApiOperation(value = "", notes = "전체 게시글 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 조회가 완료되었습니다."),
            @ApiResponse(code = 500, message = "서버 에러 메세지")
    })
    @GetMapping("posts")
    public ResponseEntity<?> readAllPost() {
        Collection<AllCategoryResDto> result = postServiceImpl.readAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}