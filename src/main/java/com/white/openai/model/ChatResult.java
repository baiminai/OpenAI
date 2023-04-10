package com.white.openai.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatResult {
    /*
    {
  "id": "chatcmpl-123",
  "object": "chat.completion",
  "created": 1677652288,
  "choices": [{
    "index": 0,
    "message": {
      "role": "assistant",
      "content": "\n\nHello there, how may I assist you today?",
    },
    "finish_reason": "stop"
  }],
  "usage": {
    "prompt_tokens": 9,
    "completion_tokens": 12,
    "total_tokens": 21
  }
}
*/
    private String id;
    private String object;
    private long created;
    private List<Choices> choices;
    private Usage usage;
    @Data
    public class Choices{
        private int index;
        private Message message;
        private String finish_reason;
    }
    @Data
    public class Message {
        private String role;
        private String content;
    }
    @Data
    public class Usage{
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}
