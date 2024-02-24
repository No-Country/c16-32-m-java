package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import com.c1632mjava.c1632mjava.Domain.Repositories.UserRepository;
import com.c1632mjava.c1632mjava.Domain.Services.MatchAlgorithmService;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchAlgorithmServiceImpl implements MatchAlgorithmService {

    public final UserRepository userRepository;
    public final MatchService matchService;

    @Override
    public List<MatchReadDto> generateAlgorithm(Long userId) {
        List<MatchReadDto> result = new ArrayList<>();
        var matchCreateDtoLists = userRepository.generateAlgorithm(userId);
        for (var matchCreate : matchCreateDtoLists) {
            var matchRead = matchService.createMatch(matchCreate);
            result.add(matchRead);
        }
        return result;
    }
}
