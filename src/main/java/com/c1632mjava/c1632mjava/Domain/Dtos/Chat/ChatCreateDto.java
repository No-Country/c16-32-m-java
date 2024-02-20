package com.c1632mjava.c1632mjava.Domain.Dtos.Chat;

import java.util.ArrayList;

public record ChatCreateDto(
        String lastMessage,
        ArrayList<String> previousMessages,
        Long senderId,
        Long receiverId
) {
}
