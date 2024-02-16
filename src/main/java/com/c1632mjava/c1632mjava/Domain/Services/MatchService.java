package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;

import java.util.List;

public interface MatchService {
    MatchReadDto findMatchById(Long id);
    List<MatchReadDto> findAllMatchesByUserId(Long userId);
    Match createMatch(MatchCreateDto dto);
    void deleteMatch(Long id);
}
