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
    @PostMapping("post")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        Post result = postServiceImpl.createPost(postDto);
        return ResponseEntity.status(HttpStatus.OK).body("글 작성이 완료되었습니다.");
    }

    // 게시글 하나 조회
    @GetMapping("post/{boardId}/{postId}")
    public ResponseEntity<?> readPost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId) {
        OnePostResDto result = postServiceImpl.readPost(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시판에 따른 모든 게시글 조회
    @GetMapping("board/{boardId}")
    public ResponseEntity<?> readCategoryPost(@PathVariable("boardId") Long boardId) {
        Collection<OneCategoryResDto> result = postServiceImpl.readCategoryPost(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 모든 게시글 조회
    @GetMapping("posts")
    public ResponseEntity<?> readAllPost() {
        Collection<AllCategoryResDto> result = postServiceImpl.readAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 마이페이지
    @GetMapping("api/my-page")
    public ResponseEntity<?> readMyPage(@RequestParam("id") String userId, @RequestParam("category") Long category) {
        Collection<?> result = postServiceImpl.readMyPage(userId, category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}