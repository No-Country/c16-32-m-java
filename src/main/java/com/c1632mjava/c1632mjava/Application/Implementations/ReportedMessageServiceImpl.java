package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.ReportedMessageMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.ReportedMessage;
import com.c1632mjava.c1632mjava.Domain.Repositories.ChatRepository;
import com.c1632mjava.c1632mjava.Domain.Repositories.ReportedMessageRepository;
import com.c1632mjava.c1632mjava.Domain.Services.ReportedMessageService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.ChatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportedMessageServiceImpl implements ReportedMessageService {
    private final ChatRepository chatRepository;
    private final ReportedMessageRepository reportedMessageRepository;
    private final ReportedMessageMapper reportedMessageMapper;

    @Transactional
    @Override
    public ReportedMessageReadDto create(ReportedMessageCreateDto dto) {
        ReportedMessage reportedMessage = this.reportedMessageMapper
                .convertCreateToReported(dto);

        reportedMessage.setDate(LocalDateTime.now());
        reportedMessage.setReviewed(Boolean.FALSE);

        Optional<Chat> optionalChat = this.chatRepository.findById(dto.chatId());

        if (optionalChat.isEmpty()) {
            throw new ChatNotFoundException("No existe chat con el ID: " + dto.chatId());
        }

        Chat chat = optionalChat.get();
        reportedMessage.setChat(chat);
        reportedMessage.setSender(chat.getSender());
        reportedMessage.setReceiver(chat.getReceiver());

        return this.reportedMessageMapper
                .convertReportedToRead(this.reportedMessageRepository.save(reportedMessage));
    }
}
