package org.nvware.core;


import org.nvware.core.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final AppConfig appConfig;

    @Autowired
    public TelegramBot(AppConfig appConfig) throws TelegramApiException {
        this.appConfig = appConfig;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return appConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return appConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Process incoming update and respond accordingly
        String userMessage = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        menuTrigger(update.getMessage());
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString()).text("You said: " + userMessage).build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean screaming = false;

    InlineKeyboardButton next = InlineKeyboardButton.builder()
            .text("Next").callbackData("next")
            .build();
    InlineKeyboardButton back = InlineKeyboardButton.builder()
            .text("Back").callbackData("back")
            .build();

    InlineKeyboardButton url = InlineKeyboardButton.builder()
            .text("Tutorial")
            .url("https://core.telegram.org/bots/api")
            .build();
    private InlineKeyboardMarkup keyboardM1 = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(next)).build();

    //Buttons are wrapped in lists since each keyboard is a set of button rows
    private InlineKeyboardMarkup keyboardM2 = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(back))
            .keyboardRow(List.of(url))
            .build();
    public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb){
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public String menuTrigger(Message msg) {
        var txt = msg.getText();
        if (msg.isCommand()) {
//            if (txt.equals("/scream"))
//                screaming = true;
//            else if (txt.equals("/whisper"))
//                screaming = false;
//            else if (txt.equals("/menu"))
//                sendMenu(id, "<b>Menu 1</b>", keyboard1);
            return "";
        }
        return "";
    }


}
