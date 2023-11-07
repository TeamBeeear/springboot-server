package com.project.gomgom.user.controller;

import com.project.gomgom.user.dto.UserDto;
import com.project.gomgom.user.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Api(tags = "사용자")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("signup")
    @ApiOperation(value = "회원가입", notes = "DB에 회원 정보를 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{\n"
                    + "    \"userId\": \"test7\",\n"
                    + "    \"userPw\": \"****\"\n"
                    + "}"),
            @ApiResponse(code = 409, message = "{\n"
                    + "    \"status\": 409,\n"
                    + "    \"code\": \"DUPLICATE_USER\",\n"
                    + "    \"message\": \"이미 존재하는 아이디입니다.\"\n"
                    + "}"),
            @ApiResponse(code = 421, message = "{\n"
                    + "    \"status\": 400,\n"
                    + "    \"code\": \"BAD_ID\",\n"
                    + "    \"message\": \"아이디를 입력하세요.\"\n"
                    + "}"),
            @ApiResponse(code = 422, message = "{\n"
                    + "    \"status\": 400,\n"
                    + "    \"code\": \"BAD_PASSWORD\",\n"
                    + "    \"message\": \"비밀번호를 입력하세요.\"\n"
                    + "}"),
    })
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
            UserDto result = userServiceImpl.signup(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
    }

    @PostMapping("login")
    @ApiOperation(value = "로그인", notes = "DB에 저장된 회원 정보로 로그인 성공 여부를 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{\n"
                    + "    \"userId\": \"test7\",\n"
                    + "    \"userPw\": \"****\"\n"
                    + "}"),
            @ApiResponse(code = 400, message = "{\n"
                    + "    \"status\": 404,\n"
                    + "    \"code\": \"USER_NOT_FOUND\",\n"
                    + "    \"message\": \"존재하지 않는 아이디입니다.\"\n"
                    + "}"),
            @ApiResponse(code = 401, message = "{\n"
                    + "    \"status\": 401,\n"
                    + "    \"code\": \"INVALID_PASSWORD\",\n"
                    + "    \"message\": \"비밀번호가 올바르지 않습니다.\"\n"
                    + "}"),
            @ApiResponse(code = 421, message = "{\n"
                    + "    \"status\": 400,\n"
                    + "    \"code\": \"BAD_ID\",\n"
                    + "    \"message\": \"아이디를 입력하세요.\"\n"
                    + "}"),
            @ApiResponse(code = 422, message = "{\n"
                    + "    \"status\": 400,\n"
                    + "    \"code\": \"BAD_PASSWORD\",\n"
                    + "    \"message\": \"비밀번호를 입력하세요.\"\n"
                    + "}"),
    })
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
            UserDto result = userServiceImpl.login(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
    }

}