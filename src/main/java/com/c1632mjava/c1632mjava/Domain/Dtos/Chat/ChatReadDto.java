package com.c1632mjava.c1632mjava.Domain.Dtos.Chat;

import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record ChatReadDto(
    Long id,
    String lastMessage,
    LocalDateTime date,
    ArrayList<String> previousMessages,
    UserReadDto sender,
    UserReadDto receiver
    // MatchReadDto?
) {
}
