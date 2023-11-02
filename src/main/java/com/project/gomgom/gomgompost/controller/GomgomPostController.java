package com.project.gomgom.gomgompost.controller;

import com.project.gomgom.gomgompost.dto.GomgomPostAllResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostOneResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostReqDto;
import com.project.gomgom.gomgompost.service.GomgomPostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("gomgom-post")

public class GomgomPostController {

    private final GomgomPostServiceImpl gomgomPostService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody GomgomPostReqDto gomgomPostReqDto) {
        gomgomPostService.createPost(gomgomPostReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 생성 완료");
    }

    @GetMapping("all")
    public ResponseEntity<?> readAllPost() {
        Collection<GomgomPostAllResDto> result = gomgomPostService.readAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("{gomgomPostId}")
    public ResponseEntity<?> readOnePost(@PathVariable("gomgomPostId") Long id) {
        GomgomPostOneResDto result = gomgomPostService.readOnePost(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}