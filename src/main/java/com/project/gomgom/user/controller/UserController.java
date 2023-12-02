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
    @ApiOperation(value = "회원가입", notes = "회원가입 시 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입이 정상적으로 수행됨", response = UserDto.class)
    })
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
            UserDto result = userServiceImpl.signup(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
    }

    @PostMapping("login")
    @ApiOperation(value = "로그인", notes = "로그인 시 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인이 정상적으로 수행됨", response = UserDto.class)
    })
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
            UserDto result = userServiceImpl.login(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
    }

}