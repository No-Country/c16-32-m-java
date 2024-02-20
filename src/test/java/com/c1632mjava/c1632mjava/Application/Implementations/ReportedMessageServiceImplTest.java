package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.ReportedMessageMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import com.c1632mjava.c1632mjava.Domain.Entities.ReportedMessage;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.ChatRepository;
import com.c1632mjava.c1632mjava.Domain.Repositories.ReportedMessageRepository;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.ChatNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportedMessageServiceImplTest {
    @MockBean
    private ChatRepository chatRepository;
    @MockBean
    private ReportedMessageRepository reportedMessageRepository;
    @Autowired
    private ReportedMessageMapper reportedMessageMapper;
    @Autowired
    private ReportedMessageServiceImpl reportedMessageService;

    private Long reportedMessageId;
    private String message;
    private LocalDateTime date;
    private Boolean reviewed;
    private Chat chat;
    private User sender;
    private User receiver;

    @BeforeEach
    void setUp() {
        this.reportedMessageId = 1L;
        this.message = "Me sentí ofendido.";
        this.date = LocalDateTime.now();
        this.reviewed = Boolean.FALSE;
        this.chat = new Chat();
        this.chat.setChatId(1L);
        this.chat.setLastMessage("Boludito");
        this.chat.setDate(LocalDateTime.now());
        this.chat.setActive(Boolean.TRUE);
        this.chat.setPreviousMessages(new ArrayList<>());
        this.chat.setSender(new User());
        this.chat.setReceiver(new User());
        this.chat.setMatch(new Match());
        this.sender = new User();
        this.receiver = new User();
    }

    @Test
    void createReportedMessageSuccessful() {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto(this.message, this.chat.getChatId());
        ReportedMessage reportedMessage = this.reportedMessageMapper.convertCreateToReported(in);

        when(this.chatRepository.findById(anyLong())).thenReturn(Optional.of(this.chat));
        when(this.reportedMessageRepository.save(any(ReportedMessage.class))).thenReturn(reportedMessage);

        ReportedMessageReadDto expected = this.reportedMessageMapper.convertReportedToRead(reportedMessage);

        //WHEN
        ReportedMessageReadDto actual = this.reportedMessageService.create(in);

        //THEN
        assertEquals(expected, actual);
        verify(this.chatRepository, times(1)).findById(anyLong());
        verify(this.reportedMessageRepository, times(1)).save(any(ReportedMessage.class));
    }

    @Test
    void createReportedMessageThrowChatNotFoundException() {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto(this.message, this.chat.getChatId());

        //WHEN AND THEN
        assertThrows(ChatNotFoundException.class, () -> {
            this.reportedMessageService.create(in);
        });
        verify(this.chatRepository, times(1)).findById(anyLong());
        verify(this.reportedMessageRepository, never()).save(any(ReportedMessage.class));
    }
}