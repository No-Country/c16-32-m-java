package com.c1632mjava.c1632mjava.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String photo;
    private Enum gender;
    private String pronouns;
    private String description;
    private Enum socialBattery;
    private String currentSong;
    private boolean active;
}