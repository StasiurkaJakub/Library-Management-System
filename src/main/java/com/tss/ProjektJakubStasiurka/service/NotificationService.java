package com.tss.ProjektJakubStasiurka.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNewBookNotification(String message) {
        messagingTemplate.convertAndSend("/topic/books", message);
    }

    public void sendReturnedBookNotification(String message) {
        messagingTemplate.convertAndSend("/topic/returns", message);
    }
}
