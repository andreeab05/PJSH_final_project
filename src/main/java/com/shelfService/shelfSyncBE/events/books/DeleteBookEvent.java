package com.shelfService.shelfSyncBE.events.books;

import org.springframework.context.ApplicationEvent;

public class DeleteBookEvent extends ApplicationEvent {
    private final Object source;
    private final Integer bookId;

    public DeleteBookEvent(Object source, Integer bookId) {
        super(source);
        this.source = source;
        this.bookId = bookId;
    }

    public Integer getBookId() {
        return bookId;
    }
}
