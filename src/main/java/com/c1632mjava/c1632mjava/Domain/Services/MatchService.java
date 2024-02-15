package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;

import java.util.List;

public interface MatchService {
    MatchReadDto findById(Long id);
    List<MatchReadDto> findAllByUserId(Long userId);
    Match create(MatchCreateDto dto);
    void delete(Long id);
}
