package com.project.gomgom.post.controller;

import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postServiceImpl;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {

        try {
            Post result = postServiceImpl.createPost(postDto);
            return ResponseEntity.status(HttpStatus.OK).body("글 작성이 완료되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시판이 존재하지 않습니다.");
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자가 존재하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("서버 오류가 발생했습니다.");
        }

    }

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<Collection<?>> readAllPost() {
        return null;
    }

    // 카테고리에 따른 모든 게시글 조회
    @GetMapping("{boardId}")
    public ResponseEntity<Collection<?>> readCategoryPost(@PathVariable("boardId") Long boardId) {
        return null;
    }

    // 게시글 하나 조회
    @GetMapping("{boardId}/{postId}")
    public ResponseEntity<?> readPost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId) {
        return null;
    }

}