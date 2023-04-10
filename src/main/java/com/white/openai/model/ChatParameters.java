package com.white.openai.model;

import lombok.Data;

@Data
public class ChatParameters {
    /*{
  "model": "gpt-3.5-turbo",
  "messages": [{"role": "user", "content": "Hello!"}]
}
*/
    private String model;
    private Messages Messages;
    @Data
    public static class Messages {
        private String role;
        private String content;
    }

}
