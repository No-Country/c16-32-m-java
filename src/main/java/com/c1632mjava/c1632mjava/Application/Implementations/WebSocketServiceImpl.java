package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Repositories.ChatRepository;
import com.c1632mjava.c1632mjava.Domain.Services.WebSocketService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.ChatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WebSocketServiceImpl implements WebSocketService {
    private final ChatRepository chatRepository;

    @Transactional
    @Override
    public void updateChat(String message) {
        // Deberiamos obtener el ID del chat desde front. Puse 1L para probar.
        Chat chat = this.chatRepository.findById(1L).orElseThrow(() -> new ChatNotFoundException(1L));
        String lastMessage = chat.getLastMessage();

        if(chat.getPreviousMessages() == null){
            chat.setPreviousMessages(new ArrayList<>());
        }

        if(lastMessage != null){
            chat.getPreviousMessages().add(lastMessage);
        }

        chat.setLastMessage(message);
        chat.setDate(LocalDateTime.now());
        this.chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    @Override
    public String getHistoryChat(Long chatId) {
        Chat chat = this.chatRepository.findById(chatId).orElseThrow(() -> new ChatNotFoundException(chatId));
        if(chat.getPreviousMessages() == null || chat.getPreviousMessages().isEmpty()){
            return "";
        }

        String history = chat.getPreviousMessages().toString();

        if (history.startsWith("[") && history.endsWith("]")) {
            history = history.substring(1, history.length() - 1);
        }

        return history;
    }
}
