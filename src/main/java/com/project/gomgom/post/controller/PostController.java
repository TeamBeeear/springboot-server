package com.project.gomgom.post.controller;

import com.project.gomgom.post.dto.AllCategoryResDto;
import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.dto.OnePostResDto;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postServiceImpl;

    // 게시글 작성
    @PostMapping("post")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {

        try {
            Post result = postServiceImpl.createPost(postDto);
            return ResponseEntity.status(HttpStatus.OK).body("글 작성이 완료되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시판이 존재하지 않습니다.");
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자가 존재하지 않습니다.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("선택지 내용이 비었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }

    }

    // 게시글 하나 조회
    @GetMapping("post/{boardId}/{postId}")
    public ResponseEntity<?> readPost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId) {

        try {
            OnePostResDto result = postServiceImpl.readPost(boardId, postId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시판이 존재하지 않습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 존재하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    // 게시판에 따른 모든 게시글 조회
    @GetMapping("board/{boardId}")
    public ResponseEntity<?> readCategoryPost(@PathVariable("boardId") Long boardId) {

        try {
            Collection<OneCategoryResDto> result = postServiceImpl.readCategoryPost(boardId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시판이 존재하지 않습니다.");
        }

    }

    // 모든 게시글 조회
    @GetMapping("posts")
    public ResponseEntity<?> readAllPost() {

        try {
            Collection<AllCategoryResDto> result = postServiceImpl.readAllPost();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.OK).body("작성된 글이 없습니다.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생하였습니다.");
        }

    }

}