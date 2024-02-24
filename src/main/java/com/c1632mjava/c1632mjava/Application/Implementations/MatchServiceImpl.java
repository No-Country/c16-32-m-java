package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
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
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final ChatService chatService;
    private final MatchMapper matchMapper;
    private final MatchRepository matchRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public MatchReadDto findMatchById(Long id) throws MatchNotFoundException {
        this.validId(id, "match");

        Optional<Match> optionalMatch = this.matchRepository.findById(id);

        if(optionalMatch.isEmpty()){
            throw new MatchNotFoundException(id);
        }

        Boolean isActive = optionalMatch.get().getActive();

        if(Boolean.FALSE.equals(isActive)){
            throw new MatchNotFoundException(id);
        }

        return this.matchMapper.convertMatchToRead(optionalMatch.get());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MatchReadDto> findAllMatchesByUserId(Long userId, Pageable paging) {
        this.validId(userId, "usuario");

        var userReadDto = userService.findUserById(userId);
        User user = userMapper.convertReadToUser(userReadDto);

        if(user == null){
            throw new UserNotFoundException(userId);
        }

        Page<Match> matches = this.matchRepository.findAllByUser1AndActiveIsTrueOrUser2AndActiveIsTrue(user, user, paging);
        return matches.map(this.matchMapper::convertMatchToRead);
    }

    @Transactional
    @Override
    public MatchReadDto createMatch(MatchCreateDto dto) {
        if(dto == null){
            throw new MatchNotNullException();
        }

        Match match = this.matchMapper.convertCreateToMatch(dto);
        match.setDateOfMatch(LocalDateTime.now());
        match.setActive(Boolean.TRUE);

        UserReadDto userReadDto1 = this.userService.findUserById(dto.user1());

        if(userReadDto1 == null){
            throw new UserNotFoundException(dto.user1());
        }

        User user1 = this.userMapper.convertReadToUser(userReadDto1);
        match.setUser1(user1);

        UserReadDto userReadDto2 = this.userService.findUserById(dto.user2());

        if(userReadDto2 == null){
            throw new UserNotFoundException(dto.user2());
        }

        User user2 = this.userMapper.convertReadToUser(userReadDto2);
        match.setUser2(user2);

        ChatCreateDto chatCreateDto = new ChatCreateDto(null, null, dto.user1(), dto.user2());

        Chat chat = this.chatService.create(chatCreateDto);
        match.setChat(chat);

        match =matchRepository.save(match);
        return  matchMapper.convertMatchToRead(match);
    }

    @Transactional
    @Override
    public void deleteMatch(Long id) {
        this.validId(id, "match");

        Optional<Match> optionalMatch = this.matchRepository.findById(id);

        if(optionalMatch.isEmpty()){
            throw new MatchNotFoundException(id);
        }

        Match match = optionalMatch.get();

        if(Boolean.FALSE.equals(match.getActive())){
            throw new MatchNotFoundException(id);
        }

        match.setActive(Boolean.FALSE);
        match.getChat().setActive(Boolean.FALSE);
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
