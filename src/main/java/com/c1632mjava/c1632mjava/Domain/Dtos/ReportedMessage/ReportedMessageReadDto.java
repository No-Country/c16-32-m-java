package com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;

import java.time.LocalDateTime;

public record ReportedMessageReadDto(
        Long reportedMessageId,
        LocalDateTime date,
        String message,
        Boolean reviewed,
        ChatReadDto chat) {
}
