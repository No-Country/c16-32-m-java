package com.c1632mjava.c1632mjava.Domain.Entities;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    Long userId=1L;
    String name="Paul";
    String email="mail@mail.com";
    String password="contra123";
    LocalDateTime birthdate=LocalDateTime.of(1990, 02, 12,0,0);
    String photo="icono_iniciosesion.png";
    Gender gender=Gender.MASCULINO;
    String pronouns="HE";
    String description="hi my name is Paul";
    SocialBattery socialBattery=SocialBattery.AWAY;
    String currentSong="tell me";
    public List<Artist> Artists;
    public List<Genre> Genres;
    public List <Long> bannedUsers;
    boolean active = true;
    User user;
    Artist ariana=new Artist(1L, "Ariana Grande");
    Artist katy=new Artist(2L, "Katy Perry");
    Artist taylor=new Artist(3L, "Taylor Swift");
    Genre genre1=new Genre(1L, "Grunge");
    Genre genre2=new Genre(2L, "Prog rock");

    @BeforeEach
    void setUp() {
        user = new User();
    }
    @Test
    void getAuthorities() {
    }

    @Test
    void getUsername() {
        user.setEmail(email);
        String emailActual=user.getEmail();
        String emailExpected="mail@mail.com";
        Assertions.assertEquals(emailExpected, emailActual);
    }

    @Test
    void isAccountNonExpired() {
    }

    @Test
    void isAccountNonLocked() {
    }

    @Test
    void isCredentialsNonExpired() {
    }

    @Test
    void isEnabled() {
    }

    @Test
    void getName() {
        user.setName(name);
        String nameActual=user.getName();
        String nameExpected="Paul";
        Assertions.assertEquals(nameExpected, nameActual);
    }

    @Test
    void getEmail() {
        user.setEmail(email);
        String emailActual=user.getEmail();
        String emailExpected="mail@mail.com";
        Assertions.assertEquals(emailExpected, emailActual);
    }

    @Test
    void getPassword() {
        user.setPassword(password);
        String passwordActual=user.getPassword();
        String passwordExpected="contra123";
        Assertions.assertEquals(passwordExpected, passwordActual);
    }

    @Test
    void getBirthdate() {
        user.setBirthdate(birthdate);
        LocalDateTime bdActual=user.getBirthdate();
        LocalDateTime bdExpected=LocalDateTime.of(1990,02,12,0,0);
        Assertions.assertEquals(bdActual, bdExpected);
    }

    @Test
    void getPhoto() {
        user.setPhoto(photo);
        String photoActual=user.getPhoto();
        String photoExpected="icono_iniciosesion.png";
        Assertions.assertEquals(photoExpected, photoActual);
    }

    @Test
    void getGender() {
        user.setGender(gender);
        Gender genderActual=user.getGender();
        Gender genderExpected=Gender.MASCULINO;
        Assertions.assertEquals(genderExpected, genderActual);
    }

    @Test
    void getPronouns() {
        user.setPronouns(pronouns);
        String pronounsActual=user.getPronouns();
        String pronounsExpected="HE";
        Assertions.assertEquals(pronounsExpected, pronounsActual);
    }

    @Test
    void getDescription() {
        user.setDescription(description);
        String descriptionActual=user.getDescription();
        String descriptionExpected="hi my name is Paul";
        Assertions.assertEquals(descriptionExpected, descriptionActual);

    }

    @Test
    void getSocialBattery() {
        user.setSocialBattery(socialBattery);
        SocialBattery sbActual=user.getSocialBattery();
        SocialBattery sbExpected=SocialBattery.AWAY;
        Assertions.assertEquals(sbExpected, sbActual);
    }

    @Test
    void getCurrentSong() {
        user.setCurrentSong(currentSong);
        String csActual=user.getCurrentSong();
        String csExpected="tell me";
        Assertions.assertEquals(csExpected, csActual);
    }

    @Test
    void getArtists() {

    }

    @Test
    void getGenres() {
    }

    @Test
    void getBannedUsers() {
    }

    @Test
    void isActive() {
    }

    @Test
    void setUserId() {
        user.setUserId(userId);
        Long userIdActual=user.getUserId();
        Long userIdExpected=1L;
        Assertions.assertEquals(userIdExpected, userIdActual);
    }

    @Test
    void setName() {
        user.setName(name);
        String nameActual=user.getName();
        String nameExpected="Paul";
        Assertions.assertEquals(nameExpected, nameActual);
    }

    @Test
    void setEmail() {
        user.setEmail(email);
        String emailActual=user.getEmail();
        String emailExpected="mail@mail.com";
        Assertions.assertEquals(emailExpected, emailActual);
    }

    @Test
    void setPassword() {
        user.setPassword(password);
        String passwordActual=user.getPassword();
        String passwordExpected="contra123";
        Assertions.assertEquals(passwordExpected, passwordActual);
    }

    @Test
    void setBirthdate() {
        user.setBirthdate(birthdate);
        LocalDateTime bdActual=user.getBirthdate();
        LocalDateTime bdExpected=LocalDateTime.of(1990,02,12,0,0);
        Assertions.assertEquals(bdActual, bdExpected);
    }

    @Test
    void setPhoto() {
        user.setPhoto(photo);
        String photoActual=user.getPhoto();
        String photoExpected="icono_iniciosesion.png";
        Assertions.assertEquals(photoExpected, photoActual);
    }

    @Test
    void setGender() {
        user.setGender(gender);
        Gender genderActual=user.getGender();
        Gender genderExpected=Gender.MASCULINO;
        Assertions.assertEquals(genderExpected, genderActual);
    }

    @Test
    void setPronouns() {
        user.setPronouns(pronouns);
        String pronounsActual=user.getPronouns();
        String pronounsExpected="HE";
        Assertions.assertEquals(pronounsExpected, pronounsActual);
    }

    @Test
    void setDescription() {
        user.setDescription(description);
        String descriptionActual=user.getDescription();
        String descriptionExpected="hi my name is Paul";
        Assertions.assertEquals(descriptionExpected, descriptionActual);
    }

    @Test
    void setSocialBattery() {
        user.setSocialBattery(socialBattery);
        SocialBattery sbActual=user.getSocialBattery();
        SocialBattery sbExpected=SocialBattery.AWAY;
        Assertions.assertEquals(sbExpected, sbActual);
    }

    @Test
    void setCurrentSong() {
        user.setCurrentSong(currentSong);
        String csActual=user.getCurrentSong();
        String csExpected="tell me";
        Assertions.assertEquals(csExpected, csActual);
    }

    @Test
    void setArtists() {
        List<Artist> testArtist= Arrays.asList(
                ariana, taylor, katy
        );
        user.setArtists(testArtist);
        assertEquals(testArtist, user.getArtists());
    }

    @Test
    void setGenres() {
    }

    @Test
    void setBannedUsers() {
    }

    @Test
    void setActive() {
        user.setActive(active);
        boolean activeActual=user.isActive();
        //boolean activeExpected=true;
        Assertions.assertTrue(activeActual);
    }

    @Test
    void getUserId() {
        user.setUserId(userId);
        Long userIdActual= user.getUserId();
        Assertions.assertEquals(1L, userIdActual);
    }
}