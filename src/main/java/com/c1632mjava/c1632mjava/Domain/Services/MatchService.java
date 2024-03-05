package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchService {
    MatchReadDto findMatchById(Long id);
    Page<MatchReadDto> findAllMatchesByUserId(Long userId, Pageable paging);
    List<MatchReadDto> findAllMatchesByUserId(Long userId);
    MatchReadDto createMatch(MatchCreateDto dto);
    void deleteMatch(Long id);
    Long findMatchByUserId1ByUserId2(Long userId1, Long userId2);
}
