package com.project.gomgom.user.controller;

import com.project.gomgom.user.dto.UserDto;
import com.project.gomgom.user.service.UserServiceImpl;
import io.swagger.annotations.Api;
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
@Api(value = "User Controller")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
            UserDto result = userServiceImpl.signup(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
            UserDto result = userServiceImpl.login(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
    }

}