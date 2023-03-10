package com.telegram.entity;

import lombok.Data;

import java.util.List;

@Data
public class AuthorEntity {
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private List<BookEntity> books;

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + lastname + '\n';
    }
}
