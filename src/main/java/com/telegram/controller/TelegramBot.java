package com.telegram.controller;

import com.telegram.components.BotCommands;
import com.telegram.components.Buttons;
import com.telegram.config.BotConfig;
import com.telegram.response.BookListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot implements BotCommands {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotTaken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String memberName = update.getMessage().getFrom().getFirstName();

            switch (messageText) {
                case "/start" -> startBot(chatId, memberName);
                case "/help" -> sendHelpText(chatId);
                case "/allbooks" -> getAllBooks(chatId);
                //case "/searchbookbyname" -> getBookByName(chatId);
                default -> log.info("Unexpected message");
            }
        }
    }

    private void startBot(Long chatId, String username) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello " + username + ", ты в телевизоре");
        message.setReplyMarkup(Buttons.inlineMarkup());

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void getAllBooks(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        ResponseEntity<BookListResponse> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:1000/api/v1/book/all", BookListResponse.class
        );
        System.out.println(responseEntity.getBody().getData());
        message.setText(responseEntity.getBody().getData().toString().replaceAll("^\\[|\\]$", ""));

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void getBookByName(long chatId, String title) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Введите название книги");
        ResponseEntity<BookListResponse> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:1000/api/v1/book/" + title, BookListResponse.class
        );
        System.out.println(responseEntity.getBody().getData());
        message.setText("Ищем книги.....");
        message.setText(responseEntity.getBody().getData().toString().replaceAll("^\\[|\\]$", ""));
    }

    private void sendHelpText(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(BotCommands.HELP_TEXT);
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
