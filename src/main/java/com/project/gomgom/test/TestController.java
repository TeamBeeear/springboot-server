package com.project.gomgom.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/test/string")
    public String basicTest() {
        return "test success";
    }

    @GetMapping("/test/response-entity")
    public ResponseEntity<?> basicResponseEntityTest() {
        return ResponseEntity.status(HttpStatus.OK).body("Response Entity 를 반환으로 하는 API 테스트 성공");
    }

}
