package com.shelfService.shelfSyncBE.events.books;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventsListener {
    @EventListener
    public void handleCreateBookEvent(CreateBookEvent event) {
        System.out.println("Book created: " + event.getBook().getTitle() +
                " by user with user ID " + event.getUserId());
    }

    @EventListener
    public void handleDeleteBookEvent(DeleteBookEvent event) {
        System.out.println("Deleted book with ID " + event.getBookId());
    }

    @EventListener
    public void handleAddReaderEvent(AddReaderEvent event) {
        System.out.println("Reader with user ID " + event.getReaderId() +
                " added to the readers of book with ID " + event.getBookId());
    }
}
