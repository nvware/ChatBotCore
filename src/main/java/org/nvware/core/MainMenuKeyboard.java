package org.nvware.core;

import org.telegram.services.Emoji;
import org.telegram.services.LocalisationService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public interface MainMenuKeyboard {

    static ReplyKeyboardMarkup getMainMenuKeyboard(String language) {
        System.out.println("getMainMenuKeyboard language : " + language);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        int lesson = 0;

        for (int i = 1; i <= 7; i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (int j = 1; j <= 6; j++) {
                keyboardRow.add(getCommand((++lesson) + "", language));
            }
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    static String getCommand(String lesson, String language) {
        return String.format(LocalisationService.getInstance().getString(lesson, language),
                Emoji.BLACK_RIGHT_POINTING_TRIANGLE.toString());
    }

    static String getCurrentCommand(String language) {
        return String.format(LocalisationService.getInstance().getString("current", language),
                Emoji.BLACK_RIGHT_POINTING_TRIANGLE.toString());
    }

    static String getForecastCommand(String language) {
        return String.format(LocalisationService.getInstance().getString("forecast", language),
                Emoji.BLACK_RIGHT_POINTING_DOUBLE_TRIANGLE.toString());
    }

    static String getSettingsCommand(String language) {
        return String.format(LocalisationService.getInstance().getString("settings", language),
                Emoji.WRENCH.toString());
    }

    static String getRateCommand(String language) {
        return String.format(LocalisationService.getInstance().getString("rateMe", language),
                Emoji.HUNDRED_POINTS_SYMBOL.toString());
    }

}
