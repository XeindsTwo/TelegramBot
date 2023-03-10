package com.telegram.response;

import com.telegram.entity.BookEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class BookListResponse extends BaseResponse {
    public BookListResponse(Iterable<BookEntity> data) {
        super(true, "Список книг");
        this.data = data;
    }

    private Iterable<BookEntity> data;

    @Override
    public String toString() {
        return "Книги из библиотеки:\n" + data;
    }
}