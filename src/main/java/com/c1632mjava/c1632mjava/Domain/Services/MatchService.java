package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchService {
    MatchReadDto findMatchById(Long id);
    Page<MatchReadDto> findAllMatchesByUserId(Long userId, Pageable paging);
    MatchReadDto createMatch(MatchCreateDto dto);
    void deleteMatch(Long id);
}
