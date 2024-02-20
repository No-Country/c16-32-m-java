package com.c1632mjava.c1632mjava.Domain.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {
    private Long chatId;
    private String lastMessage;
    private LocalDateTime date;
    private Boolean active;
    private ArrayList<String> previousMessages;
    private User sender;
    private User receiver;
    private Match match;

    @BeforeEach
    void setUp() {
        this.chatId = 1L;
        this.lastMessage = "Todo bien por suerte";
        this.date = LocalDateTime.now();
        this.active = Boolean.TRUE;
        this.previousMessages = new ArrayList<>();
        this.previousMessages.add("Hola!");
        this.previousMessages.add("Como estas?");
        this.sender = new User();
        this.receiver = new User();
        this.match = new Match();
    }

    @Test
    void noArgsConstructor() {
        //GIVEN AND WHEN
        Chat chat = new Chat();

        //THEN
        assertNotNull(chat);
        assertNull(chat.getChatId());
        assertNull(chat.getLastMessage());
        assertNull(chat.getPreviousMessages());
        assertNull(chat.getDate());
        assertNull(chat.getActive());
        assertNull(chat.getSender());
        assertNull(chat.getReceiver());
        assertNull(chat.getMatch());
    }

    @Test
    void AllArgsConstructor() {
        //GIVEN AND WHEN
        Chat chat = new Chat
                (this.chatId, this.lastMessage, this.date, this.active, this.previousMessages, this.sender, this.receiver, this.match);

        //THEN
        assertNotNull(chat);
        assertEquals(this.chatId, chat.getChatId());
        assertEquals(this.lastMessage, chat.getLastMessage());
        assertEquals(this.date, chat.getDate());
        assertEquals(this.active, chat.getActive());
        assertEquals(this.previousMessages, chat.getPreviousMessages());
        assertEquals(this.previousMessages.size(), chat.getPreviousMessages().size());
        assertEquals(this.sender, chat.getSender());
        assertEquals(this.receiver, chat.getReceiver());
        assertEquals(this.match, chat.getMatch());
    }

    @Test
    void setChatId() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setChatId(this.chatId);

        //THEN
        assertNotNull(chat);
        assertEquals(this.chatId, chat.getChatId());
    }

    @Test
    void setLastMessage() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setLastMessage(this.lastMessage);

        //THEN
        assertNotNull(chat);
        assertEquals(this.lastMessage, chat.getLastMessage());
    }

    @Test
    void setDate() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setDate(this.date);

        //THEN
        assertNotNull(chat);
        assertEquals(this.date, chat.getDate());
    }

    @Test
    void setActive() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setActive(this.active);

        //THEN
        assertNotNull(chat);
        assertEquals(this.active, chat.getActive());
    }

    @Test
    void setPreviousMessages() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setPreviousMessages(this.previousMessages);

        //THEN
        assertNotNull(chat);
        assertEquals(this.previousMessages, chat.getPreviousMessages());
        assertEquals(this.previousMessages.size(), chat.getPreviousMessages().size());
    }

    @Test
    void setSender() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setSender(this.sender);

        //THEN
        assertNotNull(chat);
        assertEquals(this.sender, chat.getSender());
    }

    @Test
    void setReceiver() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setReceiver(this.receiver);

        //THEN
        assertNotNull(chat);
        assertEquals(this.receiver, chat.getReceiver());
    }

    @Test
    void setMatch() {
        //GIVEN
        Chat chat = new Chat();

        //WHEN
        chat.setMatch(this.match);

        //THEN
        assertNotNull(chat);
        assertEquals(this.match, chat.getMatch());
    }
}