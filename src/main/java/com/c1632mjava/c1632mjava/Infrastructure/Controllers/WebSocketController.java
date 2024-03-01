package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Services.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final WebSocketService webSocketService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/broadcast")
    @SendTo("/chat")
    public String broadcastMessage(@Payload String message) {
        this.webSocketService.updateChat(message);
        return message;
    }

    @MessageMapping("/history")
    public void getPreviousMessages(@Payload Long chatId) {
        this.simpMessagingTemplate.convertAndSend("/topic/history/chat/" + chatId, this.webSocketService.getHistoryChat(chatId));
    }
}
