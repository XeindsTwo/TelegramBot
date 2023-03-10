package com.telegram.entity;

import lombok.Data;

import java.util.List;

@Data
public class PublishingEntity {
    private Long id;
    private String name;
    private String city;
    private List<BookEntity> books;

    @Override
    public String toString() {
        return name + ", " + city + '\n';
    }
}
