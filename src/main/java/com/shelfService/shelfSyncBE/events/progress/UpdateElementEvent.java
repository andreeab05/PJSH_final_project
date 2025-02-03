package com.shelfService.shelfSyncBE.events.progress;

import org.springframework.context.ApplicationEvent;

public class UpdateElementEvent extends ApplicationEvent {
    private final Integer elementId;
    private final String bookTitle;
    private final String oldProgress;
    private final String newProgress;
    private final Integer currentPages;
    private final Integer totalPages;

    public UpdateElementEvent(Object source, Integer elementId, String bookTitle, String oldProgress, String newProgress, Integer currentPages, Integer totalPages) {
        super(source);
        this.elementId = elementId;
        this.bookTitle = bookTitle;
        this.oldProgress = oldProgress;
        this.newProgress = newProgress;
        this.currentPages = currentPages;
        this.totalPages = totalPages;
    }

    public Integer getElementId() {
        return elementId;
    }

    public String getOldProgress() {
        return oldProgress;
    }

    public String getNewProgress() {
        return newProgress;
    }

    public Integer getCurrentPages() {
        return currentPages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public String getBookTitle(){
        return bookTitle;
    }
}
