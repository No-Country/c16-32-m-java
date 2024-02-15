package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Transactional
    @Override
    public Chat create(ChatCreateDto dto) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ChatReadDto findById(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatReadDto> findAllBySenderId(Long senderId) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {

    }
}
