package com.shelfService.shelfSyncBE.events.user;

import org.springframework.context.ApplicationEvent;

public class DeleteUserEvent extends ApplicationEvent {
    private final Integer uid;

    public DeleteUserEvent(Object source, Integer uid) {
        super(source);
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }
}
