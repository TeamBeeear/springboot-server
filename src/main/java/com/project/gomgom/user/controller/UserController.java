package com.project.gomgom.user.controller;

import com.project.gomgom.user.dto.UserDto;
import com.project.gomgom.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.NotAcceptableStatusException;
import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {

        try {
            UserDto result = userServiceImpl.signup(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        try {
            UserDto result = userServiceImpl.login(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(result.passwordMasked());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 아이디입니다.");
        } catch (NotAcceptableStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 올바르지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}