package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.ChatMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.UserMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.ChatRepository;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatServiceImplTest {
    @MockBean
    private ChatRepository chatRepository;
    @MockBean
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private ChatServiceImpl chatService;

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
    void createChatSuccessful() {
        //GIVEN
        ChatCreateDto in = new ChatCreateDto(this.lastMessage, this.previousMessages, 1L, 2L);
        UserReadDto senderDto = new UserReadDto(1L, "Leonardo", null, null, null, null, null, null, null, null, null, null);
        UserReadDto receiverDto = new UserReadDto(2L, "Jorge", null, null, null, null, null, null, null, null, null, null);
        User sender = this.userMapper.convertReadToUser(senderDto);
        User receiver = this.userMapper.convertReadToUser(receiverDto);

        Chat expected = this.chatMapper.convertCreateToChat(in);
        expected.setSender(sender);
        expected.setReceiver(receiver);

        when(this.userService.findUserById(anyLong()))
                .thenAnswer(invocation -> {
                    Long argument = invocation.getArgument(0);
                    if (argument.equals(1L)) {
                        return senderDto;
                    } else {
                        return receiverDto;
                    }
                });

        when(this.chatRepository.save(any(Chat.class))).thenReturn(expected);

        //WHEN
        Chat actual = this.chatService.create(in);

        //THEN
        assertEquals(expected, actual);
        verify(this.userService, times(2)).findUserById(anyLong());
        verify(this.chatRepository, times(1)).save(any(Chat.class));
    }

    @Test
    void createChatThrowChatNotNullException() {
        //GIVEN
        ChatCreateDto in = null;

        //WHEN AND THEN
        assertThrows(ChatNotNullException.class, () -> {
            this.chatService.create(in);
        });
        verify(this.userService, never()).findUserById(anyLong());
        verify(this.chatRepository, never()).save(any(Chat.class));
    }

    @Test
    void createChatThrowUserNotFoundExceptionWhenSenderNotExists() {
        ChatCreateDto in = new ChatCreateDto(this.lastMessage, this.previousMessages, 1L, 2L);

        when(this.userService.findUserById(anyLong())).thenReturn(null);

        //WHEN AND THEN
        assertThrows(UserNotFoundException.class, () -> {
            this.chatService.create(in);
        });
        verify(this.userService, times(1)).findUserById(anyLong());
        verify(this.chatRepository, never()).save(any(Chat.class));
    }

    @Test
    void createChatThrowUserNotFoundExceptionWhenReceiverNotExists() {
        ChatCreateDto in = new ChatCreateDto(this.lastMessage, this.previousMessages, 1L, 2L);

        when(this.userService.findUserById(anyLong()))
                .thenAnswer(invocation -> {
                    Long argument = invocation.getArgument(0);
                    if (argument.equals(1L)) {
                        return new UserReadDto(1L, "Leonardo", null, null, null, null, null, null, null, null, null, null);
                    } else {
                        return null;
                    }
                });

        //WHEN AND THEN
        assertThrows(UserNotFoundException.class, () -> {
            this.chatService.create(in);
        });
        verify(this.userService, times(2)).findUserById(anyLong());
        verify(this.chatRepository, never()).save(any(Chat.class));
    }

    @Test
    void findChatByIdSuccessful() {
        //GIVEN
        Long chatId = this.chatId;
        Optional<Chat> optionalChat = Optional.of(new Chat(this.chatId, this.lastMessage, this.date, this.active, this.previousMessages, this.sender,this.receiver, this.match));
        ChatReadDto expected = this.chatMapper.convertChatToRead(optionalChat.get());

        when(this.chatRepository.findById(anyLong())).thenReturn(optionalChat);

        //WHEN
        ChatReadDto actual = this.chatService.findById(chatId);

        //THEN
        assertEquals(expected, actual);
        verify(this.chatRepository, times(1)).findById(anyLong());
    }

    @Test
    void findChatByIdThrowChatNotFoundExceptionWhenChatNotExists() {
        //GIVEN
        Long chatId = this.chatId;
        Optional<Chat> optionalChat = Optional.empty();

        when(this.chatRepository.findById(anyLong())).thenReturn(optionalChat);

        //WHEN AND THEN
        assertThrows(ChatNotFoundException.class, () -> {
            this.chatService.findById(chatId);
        });
        verify(this.chatRepository, times(1)).findById(anyLong());
    }

    @Test
    void findChatByIdThrowChatNotFoundExceptionWhenActiveIsFalse() {
        //GIVEN
        Long chatId = this.chatId;
        Optional<Chat> optionalChat = Optional.of(new Chat(this.chatId, this.lastMessage, this.date, Boolean.FALSE, this.previousMessages, this.sender,this.receiver, this.match));

        when(this.chatRepository.findById(anyLong())).thenReturn(optionalChat);

        //WHEN AND THEN
        assertThrows(ChatNotFoundException.class, () -> {
            this.chatService.findById(chatId);
        });
        verify(this.chatRepository, times(1)).findById(anyLong());
    }

    @Test
    void findChatByIdThrowIdNotNullException() {
        //GIVEN
        Long chatId = null;

        //WHEN AND THEN
        assertThrows(IdNotNullException.class, () -> {
            this.chatService.findById(chatId);
        });
        verify(this.chatRepository, never()).findById(anyLong());
    }

    @Test
    void findChatByIdThrowIdLessThanOneException() {
        //GIVEN
        Long chatId = 0L;

        //WHEN AND THEN
        assertThrows(IdLessThanOneException.class, () -> {
            this.chatService.findById(chatId);
        });
        verify(this.chatRepository, never()).findById(anyLong());
    }

    @Test
    void findAllBySenderIdSuccessful() {
        //GIVEN
        Long senderId = 1L;
        PageRequest paging = PageRequest.of(0, 10);

        UserReadDto userReadDto = new UserReadDto(1L, "Leonardo", null, null, null, "El", null, null, null, null, null, null);
        Page<Chat> chats = new PageImpl<>(
                Arrays.asList(
                        new Chat(1L, this.lastMessage, this.date, this.active, this.previousMessages, this.sender, this.receiver, this.match),
                        new Chat(2L, this.lastMessage, this.date, this.active, this.previousMessages, this.sender, this.receiver, this.match)
                )
        );

        Page<ChatReadDto> expected = new PageImpl<>(chats.stream().map(this.chatMapper::convertChatToRead).toList());

        when(this.userService.findUserById(anyLong())).thenReturn(userReadDto);
        when(this.chatRepository.findAllBySenderAndActiveIsTrue(any(User.class), any(Pageable.class))).thenReturn(chats);

        //WHEN
        Page<ChatReadDto> actual = this.chatService.findAllBySenderId(senderId, paging);

        //THEN
        assertEquals(expected, actual);
        assertEquals(expected.getSize(), actual.getSize());
        verify(this.userService, times(1)).findUserById(anyLong());
        verify(this.chatRepository, times(1)).findAllBySenderAndActiveIsTrue(any(User.class), any(Pageable.class));
    }

    @Test
    void findAllBySenderIdThrowUserNotFoundException() {
        //GIVEN
        Long senderId = 1L;
        PageRequest paging = PageRequest.of(0, 10);

        when(this.userService.findUserById(anyLong())).thenReturn(null);

        //WHEN AND THEN
        assertThrows(UserNotFoundException.class, () -> {
            this.chatService.findAllBySenderId(senderId, paging);
        });
        verify(this.userService, times(1)).findUserById(anyLong());
        verify(this.chatRepository, never()).findAllBySenderAndActiveIsTrue(any(User.class), any(Pageable.class));
    }

    @Test
    void findAllBySenderIdThrowIdNotNullException() {
        //GIVEN
        Long senderId = null;
        PageRequest paging = PageRequest.of(0, 10);

        //WHEN AND THEN
        assertThrows(IdNotNullException.class, () -> {
            this.chatService.findAllBySenderId(senderId, paging);
        });
        verify(this.userService, never()).findUserById(anyLong());
        verify(this.chatRepository, never()).findAllBySenderAndActiveIsTrue(any(User.class), any(Pageable.class));
    }

    @Test
    void findAllBySenderIdThrowIdLessThanOneException() {
        //GIVEN
        Long senderId = 0L;
        PageRequest paging = PageRequest.of(0, 10);

        //WHEN AND THEN
        assertThrows(IdLessThanOneException.class, () -> {
            this.chatService.findAllBySenderId(senderId, paging);
        });
        verify(this.userService, never()).findUserById(anyLong());
        verify(this.chatRepository, never()).findAllBySenderAndActiveIsTrue(any(User.class), any(Pageable.class));
    }
}