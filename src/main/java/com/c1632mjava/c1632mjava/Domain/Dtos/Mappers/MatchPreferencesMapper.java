package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.*;
import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchPreferencesMapper {
        MatchPreferencesReadDto convertMatchPreferencesToRead(MatchPreferences matchPreferences);
        MatchPreferences convertCreateToMatchPreferences(MatchPreferencesCreateDto matchPreferencesCreateDto);
     }
