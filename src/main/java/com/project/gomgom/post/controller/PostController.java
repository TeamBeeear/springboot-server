package com.project.gomgom.post.controller;

import com.project.gomgom.post.dto.AllCategoryResDto;
import com.project.gomgom.post.dto.MyPageResDto;
import com.project.gomgom.post.dto.OneCategoryResDto;
import com.project.gomgom.post.dto.OnePostResDto;
import com.project.gomgom.post.dto.PostDto;
import com.project.gomgom.post.dto.UpdateVoteDto;
import com.project.gomgom.post.entity.Post;
import com.project.gomgom.post.service.PostServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Api(tags = "사용자 게시글")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    // 게시글 작성
    @PostMapping("post")
    @ApiOperation(value = "사용자 게시글 작성", notes = "사용자가 게시글을 작성할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글이 정상적으로 생성됨", response = Post.class)
    })
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        Post result = postServiceImpl.createPost(postDto);
        return ResponseEntity.status(HttpStatus.OK).body("글 작성이 완료되었습니다.");
    }

    // 게시글 하나 조회
    @GetMapping("post/{boardId}/{postId}")
    @ApiOperation(value = "사용자 게시글 상세 조회", notes = "사용자 게시글을 조회할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글이 정상적으로 조회됨", response = OnePostResDto.class)
    })
    public ResponseEntity<?> readPost(@PathVariable("boardId") @ApiParam(value = "게시판 기본키", example = "1") Long boardId, @PathVariable("postId") @ApiParam(value = "사용자 게시글 기본키", example = "1") Long postId) {
        OnePostResDto result = postServiceImpl.readPost(boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시판에 따른 모든 게시글 조회
    @GetMapping("board/{boardId}")
    @ApiOperation(value = "카테고리 게시글 조회", notes = "카테고리에 해당하는 게시글을 조회할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "카테고리 별 게시글이 정상적으로 조회됨", responseContainer = "List", response = OneCategoryResDto.class)
    })
    public ResponseEntity<?> readCategoryPost(@PathVariable("boardId") @ApiParam(value = "게시판 기본키", example = "1") Long boardId) {
        Collection<OneCategoryResDto> result = postServiceImpl.readCategoryPost(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 모든 게시글 조회
    @GetMapping("posts")
    @ApiOperation(value = "사용자 모든 게시글 조회", notes = "사용자의 모든 게시글을 조회할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용자의 모든 게시글이 정상적으로 조회됨", responseContainer = "List", response = AllCategoryResDto.class)
    })
    public ResponseEntity<?> readAllPost() {
        Collection<AllCategoryResDto> result = postServiceImpl.readAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 마이페이지
    @GetMapping("my-page")
    @ApiOperation(value = "마이페이지", notes = "마이페이지에 접속했을 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "마이페이지에서 게시글이 정상적으로 조회됨", responseContainer = "List", response = MyPageResDto.class)
    })
    public ResponseEntity<?> readMyPage(@RequestParam("id") String userId, @RequestParam("category") Long category) {
        Collection<MyPageResDto> result = postServiceImpl.readMyPage(userId, category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("vote/{userId}/{postId}/{selectionId}")
    @ApiOperation(value = "투표", notes = "선택지를 클릭할 때 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "투표가 정상적으로 수행됨", response = UpdateVoteDto.class)
    })
    public ResponseEntity<?> updateVote(@PathVariable("userId") @ApiParam(value = "사용자 아이디", example = "test1") String userId,
                                        @PathVariable("postId") @ApiParam(value = "사용자 게시글 기본키", example = "1") Long postId,
                                        @PathVariable("selectionId") @ApiParam(value = "첫 번째 선택지 기본키", example = "1") Long selectionId) {
        UpdateVoteDto result = postServiceImpl.updateVote(userId, postId, selectionId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}