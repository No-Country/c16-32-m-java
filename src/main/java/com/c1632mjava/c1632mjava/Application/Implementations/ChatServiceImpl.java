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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        final String USER_NOT_EXISTS_BY_ID_TEXT = "No existe usuario con el ID: ";
        if(dto == null){
            throw new RuntimeException();
        }

        Chat chat = this.chatMapper.convertCreateToChat(dto);
        chat.setDate(LocalDateTime.now());
        chat.setActive(Boolean.TRUE);

        UserReadDto senderDto = this.userService.findUserById(dto.senderId());

        if(senderDto == null){
            throw new UserNotFoundException(USER_NOT_EXISTS_BY_ID_TEXT + dto.senderId());
        }

        //mapear con usermapper
        User sender = new User();
        sender.setUserId(senderDto.userId());
        sender.setName(senderDto.name());
        sender.setBirthdate(senderDto.birthdate());
        sender.setPhoto(senderDto.photo());
        sender.setGender(senderDto.gender());
        sender.setPronouns(senderDto.pronouns());
        sender.setDescription(senderDto.description());
        sender.setSocialBattery(senderDto.socialBattery());
        sender.setCurrentSong(senderDto.currentSong());
        //sender.setArtists(senderDto.Artists());
        //sender.setGemres
        sender.setBannedUsers(senderDto.bannedUsers());

        chat.setSender(sender);

        //receiver
        UserReadDto receiverDto = this.userService.findUserById(dto.receiverId());

        if(receiverDto == null){
            throw new UserNotFoundException(USER_NOT_EXISTS_BY_ID_TEXT + dto.receiverId());
        }

        User receiver = new User();
        receiver.setUserId(receiverDto.userId());
        receiver.setName(receiverDto.name());
        receiver.setBirthdate(receiverDto.birthdate());
        receiver.setPhoto(receiverDto.photo());
        receiver.setGender(receiverDto.gender());
        receiver.setPronouns(receiverDto.pronouns());
        receiver.setDescription(receiverDto.description());
        receiver.setSocialBattery(receiverDto.socialBattery());
        receiver.setCurrentSong(receiverDto.currentSong());
        //receiver.setArtists(senderDto.Artists());
        //receiver.setGenres
        receiver.setBannedUsers(receiverDto.bannedUsers());

        chat.setReceiver(receiver);

        return this.chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    @Override
    public ChatReadDto findById(Long id) {
        this.validId(id);

        Optional<Chat> optionalChat = this.chatRepository.findById(id);

        if(optionalChat.isEmpty()){
            throw new ChatNotFoundException("No existe chat con el ID: " + id);
        }

        if(optionalChat.get().getActive().equals(Boolean.FALSE)){
            throw new ChatNotFoundException("No existe chat con el ID: " + id);
        }

        return this.chatMapper.convertChatToRead(optionalChat.get());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatReadDto> findAllBySenderId(Long senderId) {
        this.validId(senderId);

        UserReadDto userReadDto = this.userService.findUserById(senderId);

        if(userReadDto == null){
            throw new UserNotFoundException("No existe usuario con el ID: " + senderId);
        }

        // Change for UserMapper method
        User user = new User();
        user.setUserId(userReadDto.userId());

        List<Chat> chats = this.chatRepository.findAllBySender(user);

        chats = chats.stream().filter(chat -> Boolean.TRUE.equals(chat.getActive())).toList();

        return chats.stream().map(this.chatMapper::convertChatToRead).toList();
    }

    private void validId(Long id){
        if(id == null){
            throw new IdNotNullException("El ID del chat no puede ser nulo.");
        }

        if(id < 1){
            throw new IdLessThanOneException("El ID del chat no puede ser menor a 1.");
        }
    }
}
