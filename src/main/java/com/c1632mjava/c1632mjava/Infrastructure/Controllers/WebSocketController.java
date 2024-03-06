package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Services.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
    private final WebSocketService webSocketService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send/{chatId}")
    public void sendMessage(@DestinationVariable Long chatId, String message) {
        this.webSocketService.updateChat(message, chatId);
        this.messagingTemplate.convertAndSend("/topic/chat/" + chatId, message);
    }

    @MessageMapping("/history/{chatId}")
    public void getChatHistory(@DestinationVariable Long chatId, SimpMessageHeaderAccessor headerAccessor) {
        String history = this.webSocketService.getHistoryChat(chatId);
        // Envía el historial de mensajes al cliente
        messagingTemplate.convertAndSendToUser(Objects.requireNonNull(headerAccessor.getSessionId()), "/queue/history", history);
    }
}