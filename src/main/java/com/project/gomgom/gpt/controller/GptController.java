package com.project.gomgom.gpt.controller;

import com.project.gomgom.gpt.dto.GptReqDto;
import com.project.gomgom.gpt.dto.GptResDto;
import com.project.gomgom.gpt.service.GptService;
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
@Api(tags = "AI 곰곰이")
public class GptController {

    private final GptService gptService;

    @PostMapping("gpt")
    @ApiOperation(value = "Chat GPT API", notes = "곰곰이 AI를 사용하면 호출합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "{\n"
                    + "    \"answer\": \"내일은 짜장면 어떠세요?\"\n"
                    + "}")
    })
    public ResponseEntity<?> test(@RequestBody GptReqDto gptReqDto) {
        GptResDto result = gptService.getChatResponse(gptReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
