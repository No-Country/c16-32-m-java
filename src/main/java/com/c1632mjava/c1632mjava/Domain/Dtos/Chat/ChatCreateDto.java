package com.c1632mjava.c1632mjava.Domain.Dtos.Chat;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record ChatCreateDto(
        String lastMessage,
        LocalDateTime date,
        ArrayList<String> previousMessages,
        Long senderId,
        Long receiverId
) {
}
