package com.project.gomgom.gpt.controller;

import com.project.gomgom.gpt.dto.GptReqDto;
import com.project.gomgom.gpt.dto.GptResDto;
import com.project.gomgom.gpt.service.GptService;
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
public class GptController {

    private final GptService gptService;

    @PostMapping("gpt")
    public ResponseEntity<?> test(@RequestBody GptReqDto gptReqDto) {
        GptResDto result = gptService.getChatResponse(gptReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
