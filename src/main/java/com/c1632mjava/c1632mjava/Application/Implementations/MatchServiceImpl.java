package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.MatchMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.MatchRepository;
import com.c1632mjava.c1632mjava.Domain.Repositories.UserRepository;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.IdLessThanOneException;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.IdNotNullException;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.MatchNotFoundException;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchMapper matchMapper;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public MatchReadDto findById(Long id) {
        final String MATCH_NOT_EXISTS_BY_ID_TEXT = "No existe match con el ID: ";

        this.validId(id, "match");

        Optional<Match> optionalMatch = this.matchRepository.findById(id);

        if(optionalMatch.isEmpty()){
            throw new MatchNotFoundException(MATCH_NOT_EXISTS_BY_ID_TEXT + id);
        }

        Boolean isActive = optionalMatch.get().getActive();

        if(Boolean.FALSE.equals(isActive)){
            throw new MatchNotFoundException(MATCH_NOT_EXISTS_BY_ID_TEXT + id);
        }

        return this.matchMapper.convertMatchToRead(optionalMatch.get());
    }

    @Transactional(readOnly = true)
    @Override
    public List<MatchReadDto> findAllByUserId(Long userId) {
        final String USER_NOT_EXISTS_BY_ID_TEXT = "No existe usuario con el ID: ";

        this.validId(userId, "usuario");

        Optional<User> optionalUser = this.userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException(USER_NOT_EXISTS_BY_ID_TEXT + userId);
        }

        User user = optionalUser.get();
        List<Match> matches = this.matchRepository.findAllByUser1OrUser2(user, user);

        matches = matches.stream().filter(match -> Boolean.TRUE.equals(match.getActive())).toList();

        return matches.stream().map(this.matchMapper::convertMatchToRead).toList();
    }

    @Transactional
    @Override
    public Match create(MatchCreateDto dto) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        final String MATCH_NOT_EXISTS_BY_ID_TEXT = "No existe match con el ID: ";

        this.validId(id, "match");

        Optional<Match> optionalMatch = this.matchRepository.findById(id);

        if(optionalMatch.isEmpty()){
            throw new MatchNotFoundException(MATCH_NOT_EXISTS_BY_ID_TEXT + id);
        }

        Match match = optionalMatch.get();

        if(Boolean.FALSE.equals(match.getActive())){
            throw new MatchNotFoundException(MATCH_NOT_EXISTS_BY_ID_TEXT + id);
        }

        match.setActive(Boolean.FALSE);
    }

    private void validId(Long id, String subject){
        if(id == null){
            throw new IdNotNullException("El ID del " + subject + " no puede ser nulo.");
        }

        if(id < 1){
            throw new IdLessThanOneException("El ID del " + subject + " no puede ser menor a 1.");
        }
    }
}
