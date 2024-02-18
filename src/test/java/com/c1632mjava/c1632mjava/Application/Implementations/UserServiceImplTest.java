package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.ArtistDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.GenreDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.*;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserUpdateDto;
import com.c1632mjava.c1632mjava.Domain.Entities.*;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.*;
import com.c1632mjava.c1632mjava.Domain.Repositories.*;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @MockBean
    private ArtistRepository artistRepository;
    @Spy
    private ArtistMapper artistMapper = new ArtistMapperImpl();
    @MockBean
    private GenreRepository genreRepository;
    @Spy
    private GenreMapper genreMapper = new GenreMapperImpl();
    @MockBean
    private MatchPreferencesService matchPreferencesService;
    @MockBean
    private MatchRepository matchRepository;

    UserServiceImpl userService;
    Long id;
    String name;
    String password;
    String email;
    LocalDateTime birthdate;
    String photo;
    Gender gender;
    String pronouns;
    String description;
    SocialBattery socialBattery;
    String currentSong;
    boolean active;
    ArrayList<Artist> artists;
    ArrayList<Genre> genres;
    ArrayList<Long> bannedUsers;
    Long id2;
    String name2;
    String password2;
    String email2;
    LocalDateTime birthdate2;
    String photo2;
    Gender gender2;
    String pronouns2;
    String description2;
    SocialBattery socialBattery2;
    String currentSong2;
    boolean active2;

    @BeforeEach
    public void setUpUserService() {
        userService = new UserServiceImpl(userRepository, userMapper,
                artistRepository, artistMapper, genreRepository, genreMapper,
                matchPreferencesService, matchRepository);
    }

    @BeforeEach
    public void setUpUserAttributes() {
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
        id2 = 2l;
        name2 = "Mon Laferte";
        password2 = "MonLaferte123!";
        email2 = "monlaferte123@gmail.com";
        birthdate2 = LocalDateTime.of(1982, Month.JUNE, 12, 00, 00);
        photo2 = "string_photo2.jpg";
        gender2 = Gender.FEMENINO;
        pronouns2 = "ella";
        description2 = "Veeeeen y cuéntame la verdad... ♫♪";
        socialBattery2 = SocialBattery.AWAY;
        currentSong2 = "https://www.youtube.com/watch?v=WT-VE9OyAJk";
        active2 = false;
    }


    @Nested
    @DisplayName("Tests on read methods, both positive and negative cases")
    class ReadTests {

        @Test
        void shouldFindUserById() {
            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));

            var result = userService.findUserById(id);

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
            when(userRepository.findById(any())).thenReturn(Optional.empty());

            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.findUserById(id);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_ID_TEXT + id, thrown.getMessage());
        }

        @Test
        void shouldFindUserByEmail() {
            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));

            var result = userService.findUserByEmail(email);

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
        void shouldNotFindUserByEmail() {
            when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.findUserByEmail(email);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_EMAIL_TEXT + email, thrown.getMessage());
        }

        @Test
        void shouldFindAllUsers() {
            ArrayList<User> userList = new ArrayList<User>();
            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);
            userList.add(user);
            Pageable paging = PageRequest.of(0, 20);

            when(userRepository.findAllByActive(any(), any())).thenReturn(new PageImpl<>(userList));
            var result = userService.findAllUsers(true, paging);

            assertThat(userList).hasSize(1);
        }

        @Test
        void shouldNotFindUserWhenFindAllUsersIsCalled() {
            ArrayList<User> userList = new ArrayList<User>();
            Pageable paging = PageRequest.of(0, 20);

            when(userRepository.findAllByActive(any(), any())).thenReturn(new PageImpl<>(userList));
            var result = userService.findAllUsers(true, paging);

            assertThat(userList).hasSize(0);
        }

    }

    @Nested
    @DisplayName("Tests on update methods, both positive and negative cases")
    class UpdateTests {

        @Test
        void shouldUpdateUser() {
            UserUpdateDto userUpdateDto = new UserUpdateDto(id, name2,
                    password2, birthdate2, photo2, gender2, pronouns2, description2,
                    socialBattery2, currentSong2);

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            var result = userService.updateUser(userUpdateDto);

            verify(userRepository, times(1)).save(user);
            assertEquals(user.getUserId(), result.userId());
            assertEquals(user.getName(), result.name());
            assertEquals(user.getBirthdate(), result.birthdate());
            assertEquals(user.getPhoto(), result.photo());
            assertEquals(user.getGender(), result.gender());
            assertEquals(user.getPronouns(), result.pronouns());
            assertEquals(user.getDescription(), result.description());
            assertEquals(user.getSocialBattery(), result.socialBattery());
            assertEquals(user.getCurrentSong(), result.currentSong());
        }

        @Test
        void shouldNotUpdateAttributesToNull() {
            UserUpdateDto userUpdateDto = new UserUpdateDto(id, null, null,
                    null, null, null, null, null,
                    null, null
            );

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            var result = userService.updateUser(userUpdateDto);

            verify(userRepository, times(1)).save(user);
            assertNotNull(result.userId());
            assertNotNull(result.name());
            assertNotNull(result.birthdate());
            assertNotNull(result.photo());
            assertNotNull(result.gender());
            assertNotNull(result.pronouns());
            assertNotNull(result.description());
            assertNotNull(result.socialBattery());
            assertNotNull(result.currentSong());
        }

        @Test
        void shouldNotUpdateUserWithNotFoundId() {
            UserUpdateDto userUpdateDto = new UserUpdateDto(id2, name2,
                    password2, birthdate2, photo2, gender2, pronouns2, description2,
                    socialBattery2, currentSong2);

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.empty());
            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.updateUser(userUpdateDto);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_ID_TEXT + userUpdateDto.userId(), thrown.getMessage());
        }

        // TODO. Debería testearse que no haga update con el user desactivado.
    }

    @Nested
    @DisplayName("Tests on delete and reactive, both positive and negative cases")
    class ToggleTests {

        @Test
        void shouldTurnOffUser() {
            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            var result = userService.toggleUser(id);

            assertFalse(user.isActive());
        }

        @Test
        void shouldTurnOnUser() {
            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active2);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            var result = userService.toggleUser(id);

            assertTrue(user.isActive());
        }

        @Test
        void shouldNotToggleUser() {
            when(userRepository.findById(any())).thenReturn(Optional.empty());

            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.toggleUser(id);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_ID_TEXT + id, thrown.getMessage());
        }

    }

    @Nested
    @DisplayName("Tests on add liked artist to user, both positive and negative cases")
    class AddLikedArtistsTests {

        @Test
        void shouldAddLikedArtistToUser() {
            ArrayList<ArtistDto> artistDtoList = new ArrayList<>();
            ArtistDto artist = new ArtistDto(1L, "Babasónicos");
            artistDtoList.add(artist);
            Artist artistEntity = new Artist(1L, "Babasonicos");

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            when(artistRepository.save(any())).thenReturn(artistEntity);
            when(userRepository.save(any())).thenReturn(user);

            var result = userService.addLikedArtistToUser(artistDtoList, user.getUserId());

            verify(userRepository, times(1)).save(user);
            assertEquals(user.getArtists().get(0).getArtistId(), result.Artists().get(0).artistId());
            assertEquals(user.getArtists().get(0).getArtistName(), result.Artists().get(0).artistName());
        }

        @Test
        void shouldNotAddLikedArtistToUserWhenWrongUserId() {
            ArrayList<ArtistDto> artistDtoList = new ArrayList<>();
            ArtistDto artist = new ArtistDto(1L, "Babasónicos");
            artistDtoList.add(artist);

            when(userRepository.findById(any())).thenReturn(Optional.empty());

            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.addLikedArtistToUser(artistDtoList, id);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_ID_TEXT + id, thrown.getMessage());
        }

        @Test
        void shouldNotAddArtistIfItAlreadyExists() {
            ArrayList<ArtistDto> artistDtoList = new ArrayList<>();
            ArtistDto artistDto = new ArtistDto(1L, "Babasónicos");
            artistDtoList.add(artistDto);
            Artist artistEntity = new Artist(1L, "Babasonicos");
            ArrayList<Artist> artistEntityList = new ArrayList<>();
            artistEntityList.add(artistEntity);

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artistEntityList, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            when(artistRepository.existsById(any())).thenReturn(true);
            when(userRepository.save(any())).thenReturn(user);

            var result = userService.addLikedArtistToUser(artistDtoList, user.getUserId());

            verify(userRepository, times(1)).save(user);
            verify(artistRepository, times(0)).save(artistEntity);
            assertEquals(user.getArtists().get(0).getArtistId(), result.Artists().get(0).artistId());
            assertEquals(user.getArtists().get(0).getArtistName(), result.Artists().get(0).artistName());
        }
    }

    @Nested
    @DisplayName("Tests on add liked genre to user, both positive and negative cases")
    class AddLikedGenresTests {
        @Test
        void shouldAddLikedArtistToUser() {
            ArrayList<GenreDto> genreDtoList = new ArrayList<>();
            GenreDto genre = new GenreDto(1L, "K-Pop");
            genreDtoList.add(genre);
            Genre genreEntity = new Genre(1L, "K-Pop");

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            when(genreRepository.save(any())).thenReturn(genreEntity);
            when(userRepository.save(any())).thenReturn(user);

            var result = userService.addLikedGenreToUser(genreDtoList, user.getUserId());

            verify(userRepository, times(1)).save(user);
            assertEquals(user.getGenres().get(0).getGenreId(), result.Genres().get(0).genreId());
            assertEquals(user.getGenres().get(0).getGenreName(), result.Genres().get(0).genreName());
        }

        @Test
        void shouldNotAddLikedGenreToUserWhenWrongUserId() {
            ArrayList<GenreDto> genreDtoList = new ArrayList<>();
            GenreDto genre = new GenreDto(1L, "K-Pop");
            genreDtoList.add(genre);

            when(userRepository.findById(any())).thenReturn(Optional.empty());

            Exception thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
                userService.addLikedGenreToUser(genreDtoList, id);
            });

            Assertions.assertEquals(UserNotFoundException.
                    USER_NOT_EXISTS_BY_ID_TEXT + id, thrown.getMessage());
        }

        @Test
        void shouldNotAddArtistIfItAlreadyExists() {
            ArrayList<GenreDto> genreDtoList = new ArrayList<>();
            GenreDto genreDto = new GenreDto(1L, "K-Pop");
            genreDtoList.add(genreDto);
            Genre genreEntity = new Genre(1L, "K-Pop");
            ArrayList<Genre> genreEntityList = new ArrayList<>();
            genreEntityList.add(genreEntity);

            User user = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genreEntityList, bannedUsers, active);

            when(userRepository.findById(any())).thenReturn(Optional.of(user));
            when(genreRepository.existsById(any())).thenReturn(true);
            when(userRepository.save(any())).thenReturn(user);

            var result = userService.addLikedGenreToUser(genreDtoList, user.getUserId());

            verify(userRepository, times(1)).save(user);
            verify(genreRepository, times(0)).save(genreEntity);
            assertEquals(user.getGenres().get(0).getGenreId(), result.Genres().get(0).genreId());
            assertEquals(user.getGenres().get(0).getGenreName(), result.Genres().get(0).genreName());
        }
    }

    @Nested
    @DisplayName("Tests on ban, unban and find all banned users, both positive and negative cases")
    class BanningTests {
        @Test
        void shouldBanAUserByUserId() {}

        @Test
        void shouldNotBanAUserByUserIdWhenLoggedUserIdIsWrong() {}

        @Test
        void shouldNotBanAUserByUserIdWhenBannedUserIdIsWrong() {}

        @Test
        void shouldNotBanAUserByUserIdWhenBannedUserIsAlreadyBanned() {}

        @Test
        void shoudFindAllBannedUsersByUserId() {
            User user1 = new User(id, name, email, password, birthdate,
                    photo, gender, pronouns, description, socialBattery,
                    currentSong, artists, genres, bannedUsers, active);

            User user50 = new User(50L, name2, email2, password2, birthdate2,
                    photo2, gender2, pronouns2, description2, socialBattery2,
                    currentSong2, artists, genres, null, active);

            when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
            when(userRepository.findById(50L)).thenReturn(Optional.of(user50));

            var result = userService.findAllBannedByUserId(user1.getUserId());

            assertThat(result).hasSize(1);
            assertThat(result.get(0).userId()).isEqualTo(user50.getUserId());
            assertThat(result.get(0).name()).isEqualTo(user50.getName());
        }

        @Test
        void shoudReturnEmptyIfItHasNotBannedUsers() {}

        @Test
        void shouldUnbanAUserByUserId() {}

        @Test
        void shouldNotUnbanAUserByUserIdWhenLoggedUserIdIsWrong() {}

        @Test
        void shouldNotUnbanAUserByUserIdWhenBannedUserIdIsWrong() {}

        @Test
        void shouldNotUnbanAUserByUserIdWhenThatUserIsNotBanned() {}
    }
}
