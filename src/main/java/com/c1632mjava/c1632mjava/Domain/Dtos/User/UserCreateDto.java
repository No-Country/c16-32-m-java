package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.With;
import org.jetbrains.annotations.Contract;

import java.time.LocalDate;
import java.time.LocalDateTime;

@With
public record UserCreateDto(
    @NotNull String name,
    @NotNull String password,
    @NotNull String email,
    @JsonFormat (pattern = "yyyy-MM-dd")
    @NotNull LocalDate birthdate,
    @NotNull String photo,
    Gender gender,
    @NotNull String pronouns,
    @NotNull String description){
}

//TODO. Revisar validation constrains para birthdate, gender, password e email.
/*
Para password e email se puede hacer una RegExp con jackson, a través de la anotación @Email
 */

