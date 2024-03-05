package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesUpdateDto;
import jakarta.persistence.EntityNotFoundException;


public interface MatchPreferencesService {
    MatchPreferencesReadDto createMatchPreferences(MatchPreferencesCreateDto matchPreferencesCreateDto);
    MatchPreferencesReadDto findPreferencesByUserId(Long id);
    MatchPreferencesReadDto updateMatchPreferences(MatchPreferencesUpdateDto matchPreferencesUpdateDto);
    Boolean toggleMatchPreferences(Long userId);
}
