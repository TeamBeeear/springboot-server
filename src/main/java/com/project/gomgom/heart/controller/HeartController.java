package com.project.gomgom.heart.controller;

import com.project.gomgom.heart.dto.HeartDto;
import com.project.gomgom.heart.service.HeartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("heart")
public class HeartController {

    private final HeartServiceImpl heartService;

    // 좋아요 상태 보여주기 (게시글 상세페이지에서 호출해야 하는 API, 이 값에 따라 좋아요/좋아요 취소 API 호출 결정)
    @GetMapping("{userId}/{postId}")
    public ResponseEntity<?> getHeartStatus(@PathVariable("userId") String userId, @PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(heartService.getHeartStatus(userId, postId));
    }

    // 좋아요
    @PostMapping
    public ResponseEntity<?> insertHeart(@RequestBody HeartDto heartDto) {
        heartService.insertHeart(heartDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("좋아요");
    }

    // 좋아요 취소
    @DeleteMapping
    public ResponseEntity<?> deleteHeart(@RequestBody HeartDto heartDto) {
        heartService.deleteHeart(heartDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("좋아요 취소");
    }

}
