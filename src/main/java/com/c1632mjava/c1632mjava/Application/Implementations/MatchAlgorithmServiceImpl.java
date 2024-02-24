package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import com.c1632mjava.c1632mjava.Domain.Services.MatchAlgorithmService;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchAlgorithmServiceImpl implements MatchAlgorithmService {

    public final MatchPreferencesService matchPreferencesService;
    public final UserService userService;

    @Override
    public List<MatchReadDto> generateAlgorithm(Long userId) {
        var user = userService.findUserById(userId);
        var userPreferences = matchPreferencesService.findPreferencesByUserId(userId);


        return null;
    }
}
