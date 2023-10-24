package com.project.gomgom.post.controller;

import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("post")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        return null;
    }

    // 게시글 조회

}
