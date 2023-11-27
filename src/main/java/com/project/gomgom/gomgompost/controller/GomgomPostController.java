package com.project.gomgom.gomgompost.controller;

import com.project.gomgom.gomgompost.dto.GomgomPostAllResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostOneResDto;
import com.project.gomgom.gomgompost.dto.GomgomPostReqDto;
import com.project.gomgom.gomgompost.service.GomgomPostServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gomgom-post")
@Api(tags = "곰곰이 게시글")
public class GomgomPostController {

    private final GomgomPostServiceImpl gomgomPostService;

    @PostMapping
    @ApiOperation(value = "곰곰이 게시글 생성", notes = "관리자가 곰곰이 게시글을 생성할 때 호출합니다.")
    public ResponseEntity<?> createPost(@RequestBody GomgomPostReqDto gomgomPostReqDto) {
        gomgomPostService.createPost(gomgomPostReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 생성 완료");
    }

    @GetMapping("all")
    @ApiOperation(value = "곰곰이 모든 게시글 조회", notes = "곰곰이의 모든 게시글을 조회할 때 호출합니다.")
    public ResponseEntity<?> readAllPost() {
        Collection<GomgomPostAllResDto> result = gomgomPostService.readAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("{gomgomPostId}")
    @ApiOperation(value = "곰곰이 상세 게시글 조회", notes = "곰곰이의 게시글을 조회할 때 호출합니다.")
    public ResponseEntity<?> readOnePost(@PathVariable("gomgomPostId") Long id) {
        GomgomPostOneResDto result = gomgomPostService.readOnePost(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}