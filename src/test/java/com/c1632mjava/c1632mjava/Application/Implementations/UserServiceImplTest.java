/*
package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.*;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.*;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @MockBean
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistMapper artistMapper;
    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;
    @MockBean
    private MatchPreferencesService matchPreferencesService;
    @MockBean
    private MatchRepository matchRepository;

    @BeforeEach
    public void setUpService(){
        UserServiceImpl userService = new UserServiceImpl(userRepository, userMapper,
                artistRepository, artistMapper, genreRepository, genreMapper,
                matchPreferencesService, matchRepository);
        }

    @Test
     void shouldFindUserById() {
        //ARRANGE
        var id = 1l;
        var name = "Pedro Pascal";
        var password = "Pedro123!";
        var email = "pedropascal123@gmail.com";
        var birthdate = LocalDateTime.of(1980, Month.JULY, 23, 00, 00);
        var photo = "string_photo.jpg";
        var gender = Gender.MASCULINO;
        var pronouns = "él";
        var description = "Hace falta? jajaja";
        var socialBattery = SocialBattery.AVAILABLE;

        User user = new User(id, name, email, password, birthdate, photo, gender, pronouns,
                description, socialBattery, null, null, null,
                null, true);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //ACT
        var result = userService.findUserById(id);

        //ASSERT
        assertEquals(user.getUserId(), result.userId());
        assertEquals(user.getName(), result.name());
        assertEquals(user.getBirthdate(), result.birthdate());
        assertEquals(user.getUserId(), result.userId());
        assertEquals(user.getName(), result.name());
        assertEquals(user.getBirthdate(), result.birthdate());
        assertEquals(user.getPhoto(), result.photo());
        assertEquals(user.getGender(), result.gender());
        assertEquals(user.getPronouns(), result.pronouns());
        assertEquals(user.getDescription(), result.description());
        assertEquals(user.getSocialBattery(), result.socialBattery());
        assertEquals(user.getCurrentSong(), result.currentSong());
        assertEquals(user.getArtists(), result.Artists());
        assertEquals(user.getGenres(), result.Genres());
        assertEquals(user.getBannedUsers(), result.bannedUsers());
    }

    @Test
    void shouldNotFindUserById() {
        //ASSERT
        var id = 1l;
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.findUserById(userId);
             });
        assert like -> Assertions.assertEquals("some message", thrown.getMessage());
        //verify(userRepository, times(1)).findById(userId);
        //verifyNoMoreInteractions(userRepository);

    }


    @Test
    void shouldFindAllUsers() {
    Generar list vacía.
       Assert a misma cantidad de objetos.

}*/


