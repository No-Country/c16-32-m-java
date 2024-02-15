package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Dtos.ArtistDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.GenreDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record UserReadDto(
    Long userId,
    String name,
    LocalDateTime birthdate,
    String photo,
    Gender gender,
    String pronouns,
    String description,
    SocialBattery socialBattery,
    String currentSong,
    List<ArtistDto> Artists,
    List<GenreDto> Genres,
    List<Long> bannedUsers
    ){
}

