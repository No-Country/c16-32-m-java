package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.AlgorithmResult;
import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import com.c1632mjava.c1632mjava.Domain.Repositories.UserRepository;
import com.c1632mjava.c1632mjava.Domain.Services.MatchAlgorithmService;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchAlgorithmServiceImpl implements MatchAlgorithmService {

    public final UserRepository userRepository;
    public final MatchService matchService;
    public final ObjectMapper objectMapper;

    @Override
    public List<MatchReadDto> generateAlgorithm(Long userId) throws JsonProcessingException {
        List<MatchReadDto> result = new ArrayList<>();
        var matchCreateDtoLists = userRepository.generateAlgorithm(userId);
        for (var matchCreateLists : matchCreateDtoLists) {
                var matchCreateDto = new MatchCreateDto(
                        new BigDecimal(matchCreateLists[2].toString()).floatValue(),
                        (long) matchCreateLists[0],
                        (long) matchCreateLists[1]);
                var matchRead = matchService.createMatch(matchCreateDto);
                result.add(matchRead);
        }


        return result;
    }
}
