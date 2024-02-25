package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserUpdateDto(
    @NotNull Long userId,
    String name,
    String password,
    LocalDate birthdate,
    String photo,
    Gender gender,
    String pronouns,
    String description,
    SocialBattery socialBattery,
    String currentSong){
}

