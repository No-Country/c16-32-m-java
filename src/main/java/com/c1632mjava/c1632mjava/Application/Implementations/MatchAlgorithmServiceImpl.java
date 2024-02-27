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
    public List<MatchReadDto> generateAlgorithm(Long userId)
            throws JsonProcessingException {
        List<MatchReadDto> existentMatches = matchService.findAllMatchesByUserId(userId);
        List<MatchCreateDto> newMatches = generateNewMatchesList(userId);

        List<MatchReadDto> result = new ArrayList<>();
        if (!existentMatches.isEmpty()) {

            for (var existentMatch : existentMatches) {
                boolean flag1 = detectNotRenewedMatches(existentMatch, newMatches);
                if (!flag1) {
                    matchService.deleteMatch(existentMatch.matchId());
                }

                for (var newMatch : newMatches) {
                    boolean flag2 = detectMatchesToCreate(newMatch, existentMatches);
                    if (!flag2) {
                        var addingMatch = matchService.createMatch(newMatch);
                        result.add(addingMatch);
                    }
                }
            }
        } else {
            for (var newMatch : newMatches) {
                var addedMatch = matchService.createMatch(newMatch);
                result.add(addedMatch);
            }
        }
        return result;
    }


    List<MatchCreateDto> generateNewMatchesList(Long userId) {
        List<MatchCreateDto> newMatches = new ArrayList<>();
        var matchCreateDtoLists = userRepository.generateAlgorithm(userId);

        for (var matchCreateLists : matchCreateDtoLists) {
            var userId1 = (long) matchCreateLists[0];
            var userId2 = (long) matchCreateLists[1];
            var compatibilityPercentage = new BigDecimal(matchCreateLists[2].toString()).floatValue();
            var matchCreateDto = new MatchCreateDto(compatibilityPercentage, userId1, userId2);
            newMatches.add(matchCreateDto);
        }
        return newMatches;
    }

    boolean detectNotRenewedMatches(MatchReadDto existentMatch, List<MatchCreateDto> newMatches) {
        for (var newMatch : newMatches) {
            if (existentMatch.user1().userId() == newMatch.user1() &&
                    existentMatch.user2().userId() == newMatch.user2()) {
                return true;
            }
        }
        return false;
    }

    boolean detectMatchesToCreate(MatchCreateDto newMatch, List<MatchReadDto> existentMatches) {
        for (var existentMatch : existentMatches) {
            if (existentMatch.user1().userId() == newMatch.user1() &&
                    existentMatch.user2().userId() == newMatch.user2()) {
                return true;
            }
        }
        return false;
    }
}