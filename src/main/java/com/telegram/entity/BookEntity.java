package com.telegram.entity;

import lombok.Data;

@Data
public class BookEntity {
    private long id;
    private AuthorEntity author;
    private PublishingEntity publishing;
    private String title;
    private String typeBook;
    private String year;

    @Override
    public String toString() {
        return "Название книги: " + title + '\n' +
                "Автор книги: " + author + '\n' +
                "Издательство: " + publishing + '\n' +
                "Год издания: " + year + '\n' +
                "Жанр книги: " + typeBook + '\n' +
                "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n";
    }
}
