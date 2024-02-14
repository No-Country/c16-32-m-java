package com.c1632mjava.c1632mjava.Domain.Entities;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;
    private LocalDateTime birthdate;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String pronouns;
    private String description;

    @Enumerated(EnumType.STRING)
    private SocialBattery socialBattery;

    private String currentSong;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<Artist> Artists;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<Genre> Genres;

    private List <Long> bannedUsers;

    private boolean active = true;
}