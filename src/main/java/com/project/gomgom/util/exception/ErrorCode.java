package com.project.gomgom.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 빈 값
    // 유저
    BAD_ID(HttpStatus.valueOf(421), "아이디를 입력하세요."),
    BAD_PASSWORD(HttpStatus.valueOf(422), "비밀번호를 입력하세요"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다."), // 401
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다."), // 404
    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."), // 409

    // 선택지
    BAD_FIRST_SELECTION(HttpStatus.BAD_REQUEST, "첫 번째 선택지에 내용이 없습니다."), // 400
    BAD_SECOND_SELECTION(HttpStatus.BAD_REQUEST, "두 번째 선택지에 내용이 없습니다."), // 400

    // 게시판
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."), // 404
    BOARD_EMPTY(HttpStatus.OK, "게시판에 작성된 글이 없습니다."), // 200

    // 게시글
    BAD_POST_CONTENT(HttpStatus.BAD_REQUEST, "게시글에 내용이 없습니다."), // 400
    BAD_POST_TITLE(HttpStatus.BAD_REQUEST, "게시글에 제목이 없습니다."), // 400
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."), // 404
    NOT_MATCH_POST_BOARD(HttpStatus.BAD_REQUEST, "게시판과 게시글 매칭 오류가 발생하였습니다."), // 400

    // 댓글
    BAD_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "댓글에 내용이 없습니다."), // 400

    // 좋아요 기능
    ALREADY_HEARTED(HttpStatus.BAD_REQUEST, "좋아요가 이미 눌러져 있습니다."),
    EMPTY_HEARTED(HttpStatus.BAD_REQUEST, "좋아요가 이미 취소 상태입니다."),

    GOMGOM_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 곰곰이의 글입니다."),

    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리 입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
