package com.white.openai;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.white.openai.config.OpenAiConfig;
import com.white.openai.model.ChatParameters;
import com.white.openai.model.ChatResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OpenAiApplicationTests {

    @Test
    void contextLoads() {
        ChatParameters chatParameters=new ChatParameters();
        chatParameters.setModel(OpenAiConfig.MODEL);
        ChatParameters.Messages messages=new ChatParameters.Messages();
        messages.setRole("user");
        messages.setContent("hello");
        List<ChatParameters.Messages> list=new ArrayList<ChatParameters.Messages>();
        list.add(messages);
        chatParameters.setMessages(list);

        String json = JSONUtil.toJsonStr(chatParameters);
        System.out.println("开始发送请求"+json);
        String result = HttpRequest.post(OpenAiConfig.URL)
                .header("Authorization", "Bearer " + new OpenAiConfig().getKEY())
                .body(json)
                .execute()
                .body();
        ChatResult chatResult = JSONUtil.toBean(result, ChatResult.class);
        List<ChatResult.Choices> choices = chatResult.getChoices();
        for (ChatResult.Choices choice : choices) {
            System.out.println("回答"+choice.getMessage());
        }
    }

}
