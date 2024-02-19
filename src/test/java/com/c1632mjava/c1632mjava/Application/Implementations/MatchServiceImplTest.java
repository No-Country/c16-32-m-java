package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.ChatMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.MatchMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.UserMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.MatchRepository;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MatchServiceImplTest {
    @MockBean
    private ChatService chatService;
    @MockBean
    private MatchRepository matchRepository;
    @MockBean
    private UserService userService;
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private MatchServiceImpl matchService;

    @Test
    void findMatchByIdSuccessful() {
        //GIVEN
        Long matchId = 1L;
        Optional<Match> optionalMatch = Optional.of(new Match(1L, 45.6F, null, Boolean.TRUE, null, null, null));
        MatchReadDto expected = this.matchMapper.convertMatchToRead(optionalMatch.get());

        when(this.matchRepository.findById(anyLong())).thenReturn(optionalMatch);

        //WHEN
        MatchReadDto actual = this.matchService.findMatchById(matchId);

        //THEN
        assertEquals(expected, actual);
        verify(this.matchRepository).findById(anyLong());
    }

    @Test
    void findMatchByIdThrowMatchNotFoundExceptionWhenMatchNotExists() {
        //GIVEN
        Long matchId = 1L;

        when(this.matchRepository.findById(anyLong())).thenReturn(Optional.empty());

        //WHEN AND THEN
        assertThrows(MatchNotFoundException.class, () -> {
            this.matchService.findMatchById(matchId);
        });
        verify(this.matchRepository).findById(anyLong());
    }

    @Test
    void findMatchByIdThrowMatchNotFoundExceptionWhenActiveIsFalse() {
        //GIVEN
        Long matchId = 1L;
        Optional<Match> optionalMatch = Optional.of(new Match(1L, 45.6F, null, Boolean.FALSE, null, null, null));

        when(this.matchRepository.findById(anyLong())).thenReturn(optionalMatch);

        //WHEN AND THEN
        assertThrows(MatchNotFoundException.class, () -> {
            this.matchService.findMatchById(matchId);
        });
        verify(this.matchRepository).findById(anyLong());
    }

    @Test
    void findAllMatchesByUserIdSuccessful() {
        //GIVEN
        Long userId = 1L;
        PageRequest paging = PageRequest.of(0, 10);
        UserReadDto userReadDto = new UserReadDto(1L, "Leonardo", null, null, null, "El", null, null, null, null, null, null);
        Page<Match> matches = new PageImpl<>(
                Arrays.asList(
                        new Match(1L, 45.7F, null, Boolean.TRUE, null, null, null),
                        new Match(2L, 45.7F, null, Boolean.TRUE, null, null, null)
                )
        );

        Page<MatchReadDto> expected = new PageImpl<>(matches.stream().map(this.matchMapper::convertMatchToRead).toList());

        when(this.userService.findUserById(anyLong())).thenReturn(userReadDto);
        when(this.matchRepository
                .findAllByUser1AndActiveIsTrueOrUser2AndActiveIsTrue(any(User.class), any(User.class), any(Pageable.class)))
                .thenReturn(matches);

        //WHEN
        Page<MatchReadDto> actual = this.matchService.findAllMatchesByUserId(userId, paging);

        //THEN
        assertEquals(expected, actual);
        verify(this.userService).findUserById(anyLong());
        verify(this.matchRepository)
                .findAllByUser1AndActiveIsTrueOrUser2AndActiveIsTrue(any(User.class), any(User.class), any(Pageable.class));
    }

    @Test
    void findAllMatchesThrowUserNotFoundException() {
        //GIVEN
        Long userId = 1L;
        PageRequest paging = PageRequest.of(0, 10);
        UserReadDto userDto = null;

        when(this.userService.findUserById(anyLong())).thenReturn(userDto);

        //WHEN AND THEN
        assertThrows(UserNotFoundException.class, () -> {
            this.matchService.findAllMatchesByUserId(userId, paging);
        });
        verify(this.userService).findUserById(anyLong());
    }

    @Test
    void createMatchSuccessful() {
        //GIVEN
        MatchCreateDto in = new MatchCreateDto(56.8F, 1L, 2L);
        UserReadDto userReadDto1 = new UserReadDto(1L, "Francisco", null, null, null, null, null, null, null, null, null, null);
        UserReadDto userReadDto2 = new UserReadDto(2L, "Tomas", null, null, null, null, null, null, null, null, null, null);
        ChatCreateDto chatCreateDto = new ChatCreateDto("Como estas?", null, 1L, 2L);
        Chat chat = this.chatMapper.convertCreateToChat(chatCreateDto);

        Match expected = this.matchMapper.convertCreateToMatch(in);
        expected.setUser1(this.userMapper.convertReadToUser(userReadDto1));
        expected.setUser2(this.userMapper.convertReadToUser(userReadDto2));
        expected.setChat(chat);

        when(this.userService.findUserById(anyLong()))
                .thenAnswer(invocation -> {
                    Long argument = invocation.getArgument(0);
                    if (argument.equals(1L)) {
                        return userReadDto1;
                    } else {
                        return userReadDto2;
                    }
                });

        when(this.chatService.create(any(ChatCreateDto.class))).thenReturn(chat);
        when(this.matchRepository.save(any(Match.class))).thenReturn(expected);

        //WHEN
        Match actual = this.matchService.createMatch(in);

        //THEN
        assertEquals(expected, actual);
        verify(this.userService, times(2)).findUserById(anyLong());
        verify(this.chatService).create(any(ChatCreateDto.class));
        verify(this.matchRepository).save(any(Match.class));
    }

    @Test
    void createMatchThrowMatchNotNullException() {
        //GIVEN
        MatchCreateDto in = null;

        //WHEN AND THEN
        assertThrows(MatchNotNullException.class, () -> {
            this.matchService.createMatch(in);
        });
        verify(this.userService, never()).findUserById(anyLong());
        verify(this.chatService, never()).create(any(ChatCreateDto.class));
        verify(this.matchRepository, never()).save(any(Match.class));
    }

    @Test
    void createMatchThrowUserNotFoundExceptionWhenUser1NotExists() {
        //GIVEN
        MatchCreateDto in = new MatchCreateDto(56.8F, 1L, null);
        UserReadDto userReadDto = null;

        when(this.userService.findUserById(anyLong())).thenReturn(userReadDto);

        //WHEN AND THEN
        assertThrows(UserNotFoundException.class, () -> {
            this.matchService.createMatch(in);
        });
        verify(this.userService, times(1)).findUserById(anyLong());
        verify(this.chatService, never()).create(any(ChatCreateDto.class));
        verify(this.matchRepository, never()).save(any(Match.class));
    }

    @Test
    void createMatchThrowUserNotFoundExceptionWhenUser2NotExists() {
        //GIVEN
        MatchCreateDto in = new MatchCreateDto(56.8F, 1L, 2L);

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
            this.matchService.createMatch(in);
        });
        verify(this.userService, times(2)).findUserById(anyLong());
        verify(this.chatService, never()).create(any(ChatCreateDto.class));
        verify(this.matchRepository, never()).save(any(Match.class));
    }

    @Test
    void deleteMatchSuccessful() {
        //GIVEN
        Long matchId = 1L;
        Chat chat = new Chat(1L, "Adios", null, Boolean.TRUE, null, null, null, null);
        Optional<Match> optionalMatch = Optional.of(new Match(1L, 45.6F, null, Boolean.TRUE, null, null, chat));

        when(this.matchRepository.findById(anyLong())).thenReturn(optionalMatch);

        //WHEN
        this.matchService.deleteMatch(matchId);

        //THEN
        verify(this.matchRepository).findById(anyLong());
    }

    @Test
    void deleteMatchThrowMatchNotFoundExceptionWhenMatchNotExists() {
        //GIVEN
        Long matchId = 1L;

        when(this.matchRepository.findById(anyLong())).thenReturn(Optional.empty());

        //WHEN AND THEN
        assertThrows(MatchNotFoundException.class, () -> {
            this.matchService.deleteMatch(matchId);
        });
        verify(this.matchRepository).findById(anyLong());
    }

    @Test
    void deleteMatchThrowMatchNotFoundExceptionWhenActiveIsFalse() {
        //GIVEN
        Long matchId = 1L;
        Optional<Match> optionalMatch = Optional.of(new Match(1L, 45.6F, null, Boolean.FALSE, null, null, null));

        when(this.matchRepository.findById(anyLong())).thenReturn(optionalMatch);

        //WHEN AND THEN
        assertThrows(MatchNotFoundException.class, () -> {
            this.matchService.deleteMatch(matchId);
        });
        verify(this.matchRepository).findById(anyLong());
    }

    @Test
    void deleteMatchThrowIdNotNullException() {
        //GIVEN
        Long matchId = null;

        //WHEN AND THEN
        assertThrows(IdNotNullException.class, () -> {
            this.matchService.deleteMatch(matchId);
        });
        verify(this.matchRepository, never()).findById(anyLong());
    }

    @Test
    void deleteMatchThrowIdLessThanOneException() {
        //GIVEN
        Long matchId = 0L;

        //WHEN AND THEN
        assertThrows(IdLessThanOneException.class, () -> {
            this.matchService.deleteMatch(matchId);
        });
        verify(this.matchRepository, never()).findById(anyLong());
    }
}