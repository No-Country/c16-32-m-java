package com.c1632mjava.c1632mjava.Domain.Dtos.Chat;

import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;
import java.util.ArrayList;
@With
public record ChatReadDto(
    Long chatId,
    String lastMessage,
    LocalDateTime date,
    ArrayList<String> previousMessages,
    UserReadDto sender,
    UserReadDto receiver
) {
}
