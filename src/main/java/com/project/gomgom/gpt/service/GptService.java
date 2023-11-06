package com.project.gomgom.gpt.service;

import com.project.gomgom.gpt.dto.GptReqDto;
import com.project.gomgom.gpt.dto.GptResDto;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptService {

    private final ChatgptService chatgptService;

    public GptResDto getChatResponse(GptReqDto reqDto) {
        String prompt = reqDto.getQuestion();
        prompt = "너는 사용자들의 고민을 해결해주는 만능 해결사야. 답변은 30글자 안으로 문장형식으로 해줘. 내  고민은 <" + prompt + "> 야.";
        String answer = chatgptService.sendMessage(prompt);
        answer = answer.replace("\n", "");
        answer = answer.replace("\"", "");
        return GptResDto.builder().answer(answer).build();
    }

}
