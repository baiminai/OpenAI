package com.white.openai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openai")
@Data
public class OpenAiConfig {
    public static String URL="https://api.openai.com/v1/chat/completions";
    public static String MODEL= "gpt-3.5-turbo";

    private String KEY ="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";

    /*
    * ascasohoa
    * */
}
