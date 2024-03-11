package com.c1632mjava.c1632mjava.Domain.Services;

public interface WebSocketService {
    void updateChat(String message);
    String getHistoryChat(Long chatId);
}
