package com.shelfService.shelfSyncBE.events.user;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventsListener {
    @EventListener
    public void handleCreateUserEvent(CreateUserEvent event) {
        System.out.println("User created - Role: " + event.getUserRole() +
                ", Username: " + event.getUsername() +
                ", Description: " + event.getDescription());
    }

    @EventListener
    public void handleDeleteUserEvent(DeleteUserEvent event) {
        System.out.println("User deleted with UID: " + event.getUid());
    }

}
