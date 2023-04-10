package com.white.openai;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.white.openai.config.OpenAiConfig;
import com.white.openai.model.ChatParameters;
import com.white.openai.model.ChatResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OpenAiApplication {

    public static void main(String[] args) {
        System.out.println("**********************************************");
        ChatParameters chatParameters=new ChatParameters();
        chatParameters.setModel(OpenAiConfig.MODEL);
        ChatParameters.Messages messages=new ChatParameters.Messages();
        messages.setRole("user");
        messages.setContent("hello");
        chatParameters.setMessages(messages);

        String json = JSONUtil.toJsonStr(chatParameters);

        System.out.println("开始发送请求");
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

        SpringApplication.run(OpenAiApplication.class, args);
    }

}
