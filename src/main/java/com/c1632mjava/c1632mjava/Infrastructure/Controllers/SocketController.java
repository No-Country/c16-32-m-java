package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ReportedMessageCreateDto send(@NotNull ReportedMessageCreateDto message) throws Exception{
        Thread.sleep(1000);
        return new ReportedMessageCreateDto(message.message(), message.chatId());
    }
}