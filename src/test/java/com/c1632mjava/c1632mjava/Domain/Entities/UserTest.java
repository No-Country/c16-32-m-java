package com.c1632mjava.c1632mjava.Domain.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    Long id; String name; String password; String email; LocalDateTime birthdate;
    String photo; Gender gender; String pronouns; String description;
    SocialBattery socialBattery; String currentSong; boolean active;
    ArrayList<Artist> artists; ArrayList<Genre> genres; ArrayList<Long> bannedUsers;

    @BeforeEach
    public void setUpAttributes(){
        id = 1l;
        name = "Pedro Pascal";
        password = "Pedro123!";
        email = "pedropascal123@gmail.com";
        birthdate = LocalDateTime.of(1980, Month.JULY, 23, 00, 00);
        photo = "string_photo.jpg";
        gender = Gender.MASCULINO;
        pronouns = "él";
        description = "Hace falta? jajaja";
        socialBattery = SocialBattery.AVAILABLE;
        currentSong = "https://www.youtube.com/watch?v=29NM6ySmwfQ";
        active = true;
        artists = new ArrayList<>();
        genres = new ArrayList<>();
        bannedUsers = new ArrayList<>();
        bannedUsers.add(50L);
    }

    @Test
    void shouldCreateUserWithEmptyConstructor() {
        User user = new User();
        assertThat(user).isNotNull();
        assertThat(user.isActive()).isTrue();
    }

    @Test
    void shouldCreateUserWithFullConstructor() {

        User user = new User(id, name, email, password, birthdate, photo, gender, pronouns,
                description, socialBattery, currentSong, artists, genres, bannedUsers, active);

        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPhoto()).isEqualTo(photo);
        assertThat(user.getPronouns()).isEqualTo( pronouns);
        assertThat(user.getDescription()).isEqualTo(description);
        assertThat(user.getCurrentSong()).isEqualTo(currentSong);
        assertThat(user.getGender()).isEqualTo(gender);
        assertThat(user.getSocialBattery()).isEqualTo(socialBattery);
        assertThat(user.getBirthdate()).isEqualTo(birthdate);
        assertThat(user.isActive()).isTrue();
        assertThat(user.getArtists()).hasSize(0);
        assertThat(user.getGenres()).hasSize(0);
        assertThat(user.getBannedUsers()).hasSize(1);
    }

    @Test
    void shouldSetAttributesToUser() {
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setPhoto(photo);
        user.setGender(gender);
        user.setPronouns(pronouns);
        user.setDescription(description);
        user.setSocialBattery(socialBattery);
        user.setCurrentSong(currentSong);
        user.setActive(active);
        user.setArtists(artists);
        user.setGenres(genres);
        user.setBannedUsers(bannedUsers);

        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPhoto()).isEqualTo(photo);
        assertThat(user.getPronouns()).isEqualTo( pronouns);
        assertThat(user.getDescription()).isEqualTo(description);
        assertThat(user.getCurrentSong()).isEqualTo(currentSong);
        assertThat(user.getGender()).isEqualTo(gender);
        assertThat(user.getSocialBattery()).isEqualTo(socialBattery);
        assertThat(user.getBirthdate()).isEqualTo(birthdate);
        assertThat(user.isActive()).isTrue();
        assertThat(user.getArtists()).hasSize(0);
        assertThat(user.getGenres()).hasSize(0);
        assertThat(user.getBannedUsers()).hasSize(1);
    }

}
