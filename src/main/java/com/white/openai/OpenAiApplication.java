package com.white.openai;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.white.openai.config.OpenAiConfig;
import com.white.openai.model.ChatParameters;
import com.white.openai.model.ChatResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
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
        List<ChatParameters.Messages> list=new ArrayList<ChatParameters.Messages>();
        list.add(messages);
        chatParameters.setMessages(list);

        String json = JSONUtil.toJsonStr(chatParameters);

        System.out.println("开始发送请求");
        String result = HttpRequest.post(OpenAiConfig.URL)
                .header("Authorization", "Bearer " + new OpenAiConfig().getKEY())
                .body(json)
                .execute()
                .body();
/*
* {
"error": {
"message": "Incorrect API key provided: sk-D3Q3T***************************************pj14.
* You can find your API key at https://platform.openai.com/account/api-keys.";,
"type": "invalid_request_error",
"param": null,
"code": "invalid_api_key"
}
}
* curl https://api.openai.com/v1/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
    "model": "text-davinci-003",
    "prompt": "Say this is a test",
    "max_tokens": 7,
    "temperature": 0
  }'
curl https://api.openai.com/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
    "model": "gpt-3.5-turbo",
    "messages": [{"role": "user", "content": "Hello!"}]
  }'

*
*
* */
        if (result!=null) {
            System.out.println(result);
            ChatResult chatResult = JSONUtil.toBean(result, ChatResult.class);
            List<ChatResult.Choices> choices = chatResult.getChoices();
            for (ChatResult.Choices choice : choices) {
                System.out.println("回答"+choice.getMessage());
            }
        }else {
            System.out.println("空值啊你");
        }

        SpringApplication.run(OpenAiApplication.class, args);
    }

}
