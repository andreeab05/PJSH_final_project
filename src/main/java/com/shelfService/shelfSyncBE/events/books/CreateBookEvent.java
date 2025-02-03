package com.shelfService.shelfSyncBE.events.books;

import com.shelfService.shelfSyncBE.entity.Book;
import org.springframework.context.ApplicationEvent;

public class CreateBookEvent extends ApplicationEvent {
    private final Object source;
    private final Integer userId;
    private final Book book;

    public CreateBookEvent(Object source, Integer userId, Book book) {
        super(source);
        this.source = source;
        this.userId = userId;
        this.book = book;
    }

    public Integer getUserId() {
        return userId;
    }

    public Book getBook() {
        return book;
    }
}
