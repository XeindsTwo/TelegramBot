package com.telegram.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "Старт"),
            new BotCommand("/help", "Помощь"),
            new BotCommand("/allbooks", "Получить все книги"),
            new BotCommand("/searchbookbyname", "Найти книгу по названию")
    );

    String HELP_TEXT = "Этот бот поможет вам посчитать количество сообщений в чате:\n\n" +
            "/start - Стартануть\n" +
            "/help - Меню помощи\n" +
            "/allBooks - получиь все книги\n" +
            "/searchbookbyname - найти книгу по её названию";
}