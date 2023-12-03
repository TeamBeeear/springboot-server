package com.project.gomgom.heart.controller;

import com.project.gomgom.heart.dto.HeartDto;
import com.project.gomgom.heart.service.HeartServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/heart")
@Api(tags = "좋아요 기능")
public class HeartController {

    private final HeartServiceImpl heartService;

    // 좋아요 상태 보여주기 (게시글 상세페이지에서 호출해야 하는 API, 이 값에 따라 좋아요/좋아요 취소 API 호출 결정)
    @GetMapping("{userId}/{postId}")
    @ApiOperation(value = "좋아요 상태 보여주기", notes = "사용자가 게시글을 좋아요를 여부를 확인할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "좋아요 조회가 성공적으로 수행됨", response = Boolean.class)
    })
    public ResponseEntity<?> getHeartStatus(@PathVariable("userId") String userId, @PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(heartService.getHeartStatus(userId, postId));
    }

    // 좋아요
    @PostMapping
    @ApiOperation(value = "좋아요 누르기", notes = "좋아요를 누른 경우 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "좋아요가 정상적으로 실행됨")
    })
    public ResponseEntity<?> insertHeart(@RequestBody HeartDto heartDto) {
        heartService.insertHeart(heartDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("좋아요");
    }

    // 좋아요 취소
    @DeleteMapping
    @ApiOperation(value = "좋아요 취소하기", notes = "좋아요 취소를 누른 경우 호출됩니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "좋아요 취소가 정상적으로 실행됨")
    })
    public ResponseEntity<?> deleteHeart(@RequestBody HeartDto heartDto) {
        heartService.deleteHeart(heartDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("좋아요 취소");
    }

}
