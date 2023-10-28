package com.project.gomgom.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 유저
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다."), // 401
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다."), // 404
    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."); // 409

    private final HttpStatus httpStatus;
    private final String message;

}
