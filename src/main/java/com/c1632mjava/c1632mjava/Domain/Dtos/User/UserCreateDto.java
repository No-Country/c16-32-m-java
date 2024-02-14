package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UserCreateDto(


    @NotNull String name,
    @NotNull String password,
    @NotNull String email,
    @NotNull LocalDateTime birthdate,
    @NotNull String photo,
    @NotBlank Gender gender,
    @NotNull String pronouns,
    @NotNull String description){
    public UserCreateDto() {
        this("", "", "", LocalDateTime.now(), "", Gender.OTRX, "", "");
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String photo() {
        return photo;
    }

    @Override
    public Gender gender(){
        return gender;
    }

    @Override
    public String pronouns() {
        return pronouns;
    }

    @Override
    public String description() {
        return description;
    }

}




