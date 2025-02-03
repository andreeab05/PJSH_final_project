package com.shelfService.shelfSyncBE.events.books;

import org.springframework.context.ApplicationEvent;

public class AddReaderEvent extends ApplicationEvent {
    private final Object source;
    private final Integer bookId;
    private final Integer readerId;

    public AddReaderEvent(Object source, Integer bookId, Integer readerId) {
        super(source);
        this.source = source;
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }
}
