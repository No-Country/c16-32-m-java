package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WithMockUser
@WebMvcTest(ChatController.class)
class ChatControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ChatService chatService;
    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/chats";
    }

    @Test
    void findChatByIdSuccessful() throws Exception {
        //GIVEN
        Long chatId = 1L;
        ChatReadDto out = new ChatReadDto(1L, "Hola!", null, null, null, null);

        when(this.chatService.findById(anyLong())).thenReturn(out);

        //WHEN
        this.mvc.perform(get(this.url.concat("/{id}"), chatId)
                .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(out)));
        verify(this.chatService).findById(anyLong());
    }

    @Test
    void findAllChatsBySenderIdSuccessful() throws Exception {
        //GIVEN
        Long senderId = 1L;
        List<ChatReadDto> chats = Arrays.asList(
                new ChatReadDto(1L, "Hola!", null, null, null, null),
                new ChatReadDto(2L, "Adios!", null, null, null, null)
        );
        Page<ChatReadDto> out = new PageImpl<>(chats);

        when(this.chatService.findAllBySenderId(anyLong(), any(Pageable.class))).thenReturn(out);

        //WHEN
        this.mvc.perform(get(this.url.concat("/senders/{senderId}"), senderId)
                .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.chatService).findAllBySenderId(anyLong(), any(Pageable.class));
    }
}