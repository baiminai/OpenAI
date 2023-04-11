package com.white.openai.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.white.openai.config.OpenAiConfig;
import com.white.openai.model.ChatParameters;
import com.white.openai.model.ChatResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("chat")
public class Chat {

    @PostMapping("/query")
    public ChatResult findUser(@PathVariable ChatParameters.Messages messages){
        ChatParameters chatParameters=new ChatParameters();
        chatParameters.setModel(OpenAiConfig.MODEL);
        List<ChatParameters.Messages> list=new ArrayList<ChatParameters.Messages>();
        list.add(messages);
        chatParameters.setMessages(list);

        String json = JSONUtil.toJsonStr(chatParameters);

        String result = HttpRequest.post(OpenAiConfig.URL)
                .header("Authorization", "Bearer " + new OpenAiConfig().getKEY())
                .body(json)
                .execute()
                .body();
        return JSONUtil.toBean(result, ChatResult.class);
    }
}
