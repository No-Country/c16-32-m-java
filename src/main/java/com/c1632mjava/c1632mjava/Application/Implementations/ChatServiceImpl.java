package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.ChatMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.UserMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.ChatRepository;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public Chat create(ChatCreateDto dto) {
        if(dto == null){
            throw new ChatNotNullException();
        }

        Chat chat = this.chatMapper.convertCreateToChat(dto);
        chat.setDate(LocalDateTime.now());
        chat.setActive(Boolean.TRUE);

        UserReadDto senderDto = this.userService.findUserById(dto.senderId());

        if(senderDto == null){
            throw new UserNotFoundException(dto.senderId());
        }

        User sender = this.userMapper.convertReadToUser(senderDto);
        chat.setSender(sender);

        UserReadDto receiverDto = this.userService.findUserById(dto.receiverId());

        if(receiverDto == null){
            throw new UserNotFoundException(dto.receiverId());
        }

        User receiver = this.userMapper.convertReadToUser(receiverDto);

        chat.setReceiver(receiver);

        return this.chatRepository.save(chat);
    }

    @Transactional
    @Override
    public void save(ChatCreateDto dto) {
        Optional<Chat> optionalChat = chatRepository.findBySenderReceiver(dto.senderId(), dto.receiverId());
        if (optionalChat.isPresent()) {
            // El chat ya existe, actualiza el chat existente
            Chat existingChat = optionalChat.get();

            Chat updatedChat = chatMapper.convertCreateToChat(dto);
            updatedChat.setLastMessage(dto.lastMessage());
            ArrayList<String> previousMessages = existingChat.getPreviousMessages();
            if (previousMessages == null) {
                previousMessages = new ArrayList<>();
            }
            // Agrega el nuevo mensaje a la lista de mensajes previos
            previousMessages.add(dto.lastMessage());
            updatedChat.setPreviousMessages(previousMessages);
            chatRepository.save(updatedChat);
        } else {
            // El chat no existe, se crea
            Chat chat = this.chatMapper.convertCreateToChat(dto);
            chat.setDate(LocalDateTime.now());
            chat.setActive(Boolean.TRUE);

            UserReadDto senderDto = this.userService.findUserById(dto.senderId());

            ArrayList<String> previousMessages = chat.getPreviousMessages();
            if (previousMessages == null) {
                previousMessages = new ArrayList<>();
            }
            // Agrega el nuevo mensaje a la lista de mensajes previos
            previousMessages.add(dto.lastMessage());
            chat.setPreviousMessages(previousMessages);

            if(senderDto == null){
                throw new UserNotFoundException(dto.senderId());
            }

            User sender = this.userMapper.convertReadToUser(senderDto);
            chat.setSender(sender);

            UserReadDto receiverDto = this.userService.findUserById(dto.receiverId());

            if(receiverDto == null){
                throw new UserNotFoundException(dto.receiverId());
            }

            User receiver = this.userMapper.convertReadToUser(receiverDto);

            chat.setReceiver(receiver);
            chatRepository.save(chat);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ChatReadDto findById(Long id) {
        this.validId(id, "chat");

        Optional<Chat> optionalChat = this.chatRepository.findById(id);

        if(optionalChat.isEmpty()){
            throw new ChatNotFoundException(id);
        }

        if(optionalChat.get().getActive().equals(Boolean.FALSE)){
            throw new ChatNotFoundException(id);
        }

        return this.chatMapper.convertChatToRead(optionalChat.get());
    }



    @Transactional(readOnly = true)
    @Override
    public Page<ChatReadDto> findAllBySenderId(Long senderId, Pageable paging) {
        this.validId(senderId, "usuario");

        UserReadDto userReadDto = this.userService.findUserById(senderId);

        if(userReadDto == null){
            throw new UserNotFoundException(senderId);
        }

        User user = this.userMapper.convertReadToUser(userReadDto);

        Page<Chat> chats = this.chatRepository.findAllBySenderAndActiveIsTrue(user, paging);

        return chats.map(this.chatMapper::convertChatToRead);
    }

    @Transactional(readOnly = true)
    @Override
    public Chat findByReceiverSenderId(ChatCreateDto dto){
        return chatRepository.findBySenderReceiver(dto.senderId(), dto.receiverId())
                .orElse(null);
    }

    private void validId(Long id, String subject){
        if(id == null){
            throw new IdNotNullException(subject);
        }

        if(id < 1){
            throw new IdLessThanOneException(subject);
        }
    }
}