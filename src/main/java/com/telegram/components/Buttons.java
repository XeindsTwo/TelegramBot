package com.telegram.components;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Старт");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Помощь");
    private static final InlineKeyboardButton ALL_BUTTON = new InlineKeyboardButton("Получить все книги");
    private static final InlineKeyboardButton SEARCHBYNAME_BUTTON = new InlineKeyboardButton("Получить все книги");

    public static InlineKeyboardMarkup inlineMarkup() {
        START_BUTTON.setCallbackData("/start");
        HELP_BUTTON.setCallbackData("/help");
        ALL_BUTTON.setCallbackData("/allbooks");
        SEARCHBYNAME_BUTTON.setCallbackData("/searchbookbyname");

        List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON);
        List<InlineKeyboardButton> rowInlineTwo = List.of(ALL_BUTTON, SEARCHBYNAME_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline, rowInlineTwo);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
}