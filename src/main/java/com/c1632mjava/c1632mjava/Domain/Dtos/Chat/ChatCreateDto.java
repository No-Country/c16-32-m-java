package com.c1632mjava.c1632mjava.Domain.Dtos.Chat;

import lombok.With;

import java.util.ArrayList;

@With
public record ChatCreateDto(
        String lastMessage,
        ArrayList<String> previousMessages,
        Long senderId,
        Long receiverId
) {
}
