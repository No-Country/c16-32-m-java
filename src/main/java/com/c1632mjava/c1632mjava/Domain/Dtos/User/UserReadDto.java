package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;

import java.time.LocalDateTime;

public record UserReadDto(
    Long userId,
    String name,
    LocalDateTime birthdate,
    String photo,
    Gender gender,
    String pronouns,
    String description,
    SocialBattery socialBattery,
    String currentSong){
}

