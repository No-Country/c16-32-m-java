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

    @Test
    void createReportedMessageSuccessful() {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto("Me sentí ofendido por este mensaje.", 1L);
        ReportedMessage reportedMessage = this.reportedMessageMapper.convertCreateToReported(in);
        Chat chat = new Chat();
        chat.setChatId(1L);
        chat.setLastMessage("Boludito");
        chat.setDate(LocalDateTime.now());
        chat.setActive(Boolean.TRUE);
        chat.setPreviousMessages(new ArrayList<>());
        chat.setSender(new User());
        chat.setReceiver(new User());
        chat.setMatch(new Match());

        when(this.chatRepository.findById(anyLong())).thenReturn(Optional.of(chat));
        when(this.reportedMessageRepository.save(any(ReportedMessage.class))).thenReturn(reportedMessage);

        ReportedMessageReadDto expected = this.reportedMessageMapper.convertReportedToRead(reportedMessage);

        //WHEN
        ReportedMessageReadDto actual = this.reportedMessageService.create(in);

        //THEN
        assertEquals(expected, actual);
        verify(this.chatRepository).findById(anyLong());
        verify(this.reportedMessageRepository).save(any(ReportedMessage.class));
    }

    @Test
    void createReportedMessageThrowChatNotFoundException() {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto("Me sentí ofendido por este mensaje.", 1L);

        //WHEN AND THEN
        assertThrows(ChatNotFoundException.class, () -> {
            this.reportedMessageService.create(in);
        });
        verify(this.chatRepository).findById(anyLong());
        verify(this.reportedMessageRepository, never()).save(any(ReportedMessage.class));
    }
}