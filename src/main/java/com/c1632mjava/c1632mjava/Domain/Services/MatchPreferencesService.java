package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesUpdateDto;
import jakarta.persistence.EntityNotFoundException;


public interface MatchPreferencesService {
    MatchPreferencesReadDto createMatchPreferences(MatchPreferencesCreateDto matchPreferencesCreateDto);
    MatchPreferencesReadDto findPreferencesByUserId(Long id) throws EntityNotFoundException;
    MatchPreferencesReadDto updateMatchPreferences(MatchPreferencesUpdateDto matchPreferencesUpdateDto) throws EntityNotFoundException;
    Boolean toggleMatchPreferences(Long userId) throws EntityNotFoundException;
}
