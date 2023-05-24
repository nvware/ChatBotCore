package org.nvware.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramChatBotApplication {

    public static void main(String[] args) {
        //ApiContextInitializer.init();
        SpringApplication.run(TelegramChatBotApplication.class, args);
    }

}
