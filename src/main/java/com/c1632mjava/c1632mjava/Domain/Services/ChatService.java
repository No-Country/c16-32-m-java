package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;

import java.util.List;

public interface ChatService {
    Chat create(ChatCreateDto dto);
    ChatReadDto findById(Long id);
    List<ChatReadDto> findAllBySenderId(Long senderId);
}
