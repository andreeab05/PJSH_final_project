package com.shelfService.shelfSyncBE.events.progress;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateElementEventListener {
    //@EventListener
    @EventListener(condition = "#event.oldProgress != #event.newProgress || #event.currentPages > (#event.totalPages / 2)")
    public void handleUpdateElementEvent(UpdateElementEvent event) {
        System.out.println("Book "+ event.getBookTitle() + " progress updated " +
                " with progress going from " + event.getOldProgress() + " to " + event.getNewProgress() +
                " and read pages: " + event.getCurrentPages() + " / " + event.getTotalPages());
    }
}
