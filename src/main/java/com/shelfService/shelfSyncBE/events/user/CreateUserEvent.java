package com.shelfService.shelfSyncBE.events.user;

import org.springframework.context.ApplicationEvent;

public class CreateUserEvent extends ApplicationEvent {
    private final String username;
    private final String description;
    private final String userRole;

    public CreateUserEvent(Object source, String username, String description, String userRole) {
        super(source);
        this.username = username;
        this.description = description;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getUserRole() {
        return userRole;
    }
}

