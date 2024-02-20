package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.ReportedMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ReportedMessageControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ReportedMessageService reportedMessageService;

    private ObjectMapper objectMapper;
    private String url;
    private String message;
    private Long chatId;
    private Long reportedMessageId;
    private LocalDateTime date;
    private Boolean reviewed;
    private ChatReadDto chat;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/reported-messages";
        this.message = "Me sentí ofendido";
        this.chatId = 1L;
        this.reportedMessageId = 1L;
        this.date = null;
        this.reviewed = Boolean.FALSE;
        this.chat = null;
    }

    @Test
    void createReportedMessageSuccessful() throws Exception {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto(this.message, this.chatId);
        ReportedMessageReadDto out = new ReportedMessageReadDto(this.reportedMessageId, this.date, this.message, this.reviewed, this.chat);

        when(this.reportedMessageService.create(any(ReportedMessageCreateDto.class))).thenReturn(out);

        //WHEN
        this.mvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(in)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(out)));

        verify(this.reportedMessageService, times(1)).create(any(ReportedMessageCreateDto.class));
    }

    @Test
    void createReportedMessageThrowMethodArgumentNotValidException() throws Exception {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto(null, null);

        //WHEN
        MvcResult result = this.mvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(in)))
                //THEN
                .andExpect(status().isBadRequest()).andReturn();
        Exception exception = result.getResolvedException();
        assert exception != null;
        assertEquals(MethodArgumentNotValidException.class, exception.getClass());
        verify(this.reportedMessageService, never()).create(any(ReportedMessageCreateDto.class));
    }
}