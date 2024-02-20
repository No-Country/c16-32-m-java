package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Application.Implementations.UserServiceImpl;
import com.c1632mjava.c1632mjava.Domain.Dtos.*;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import com.c1632mjava.c1632mjava.Domain.Entities.*;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.*;
import com.c1632mjava.c1632mjava.Domain.Services.ReportedMessageService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //Test de SpringBoot porque Controller.
@AutoConfigureMockMvc //Configura el manejo de endpoints (post, put, get, delete, patch).
@WithMockUser //Habilita la autenticación (evita el error 403)
@AutoConfigureJsonTesters //Permite manejarse con DTO en lugar de requerir JSON.
class UserTestControllerTest {

    @Autowired //Como el test está fuera del contexto de SpringBoot no pueden hacerse por constructor.
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserCreateDto> userCreateDtoJacksonTester;
    @Autowired
    private JacksonTester<UserReadDto> userReadDtoJacksonTester;
    @Autowired
    private JacksonTester<UserUpdateDto> userUpdateDtoJacksonTester;
    @Autowired
    private JacksonTester<Page<UserReadDto>> pageUserReadDtoJacksonTester;
    @Autowired
    private JacksonTester<List<UserReadDto>> listUserReadDtoJacksonTester;
    @Autowired
    private JacksonTester<List<ArtistDto>> artistDtoJacksonTester;
    @Autowired
    private JacksonTester<List<GenreDto>> genreDtoJacksonTester;

    @MockBean //Suplanta el service y evita hacer la llamada a base de datos original.
    private UserServiceImpl userServiceImpl;

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
    ArrayList<ArtistDto> artists;
    ArrayList<GenreDto> genres;
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
    public void setUpUserAttributes() {
        id = 1l;
        name = "Pedro Pascal";
        password = "Pedro123!";
        email = "pedropascal123@gmail.com";
        birthdate = LocalDateTime.of(1980, Month.JULY, 23, 00, 00);
        photo = "string_photo.jpg";
        gender = Gender.MASCULINO;
        pronouns = "el";
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
        description2 = "Veeeeen y cuentame la verdad...";
        socialBattery2 = SocialBattery.AWAY;
        currentSong2 = "https://www.youtube.com/watch?v=WT-VE9OyAJk";
        active2 = false;
    }

    /*
        @Test
        void createReportedMessageSuccessful() throws Exception {
            //GIVEN
            ReportedMessageCreateDto in =
            new ReportedMessageCreateDto("Me sentí ofendido por este mensaje.", 1L);
            ReportedMessageReadDto out =
            new ReportedMessageReadDto(1L, null, "Me sentí ofendido por este mensaje.",
            Boolean.FALSE, null);

            when(this.reportedMessageService.create(any(ReportedMessageCreateDto.class)))
            .thenReturn(out);

            //WHEN
            this.mvc.perform(post(this.url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(this.objectMapper.writeValueAsString(in)))
                    //THEN
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(this.objectMapper.writeValueAsString(out)));
            verify(this.reportedMessageService).create(any(ReportedMessageCreateDto.class));
        }

        @Test
        void createReportedMessageThrowMethodArgumentNotValidException() throws Exception {
            //GIVEN
            ReportedMessageCreateDto in = new ReportedMessageCreateDto(null, null);

            //WHEN
            MvcResult result = this.mvc.perform(post(this.url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(this.objectMapper.writeValueAsString(in)))
                    //THEN
                    .andExpect(status().isBadRequest()).andReturn();
            Exception exception = result.getResolvedException();
            assert exception != null;
            assertEquals(MethodArgumentNotValidException.class, exception.getClass());
            verify(this.reportedMessageService, never()).create(any(ReportedMessageCreateDto
            .class));
        }
    }
*/

    @Nested
    @DisplayName("Tests on register - in progress, surely not working.")
    class RegisterTests {

        @Test
        @DisplayName("Should return http200 when provided data is valid.")
        void registerUserTest1() throws Exception {
        }

        @Test
        @DisplayName("Should return http500 when provided data is invalid????")
        void registerUserTest2() throws Exception {
        }
    }

    @Nested
    @DisplayName("Tests on read users, both positive and negative cases")
    class ReadUsersTest {

        @Test
        @DisplayName("Should return http200 when find all users. Wrong case might not " +
                "be needed since this controller would just throw an empty page of users.")
        void findAllUsersTest1() throws Exception {
            Pageable paging = PageRequest.of(0, 10);
            ArrayList<UserReadDto> userList = new ArrayList<>();
            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo,
                    gender, pronouns, description, socialBattery, currentSong,
                    artists, genres, bannedUsers);
            userList.add(userReadDto);
            Page<UserReadDto> pageUserReadDto = new PageImpl<>(userList);

            when(userServiceImpl.findAllUsers(true, paging))
                    .thenReturn(pageUserReadDto);

            var response = mvc.perform(get("/users/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http200 when find user by id.")
        void findUserByIdTest1() throws Exception {
            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo,
                    gender, pronouns, description, socialBattery, currentSong,
                    artists, genres, bannedUsers);

            when(userServiceImpl.findUserById(userReadDto.userId()))
                    .thenReturn(userReadDto);

            var response = mvc.perform(get("/users/id/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            var expectedJson = userReadDtoJacksonTester.write(userReadDto).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http404 when find user by id.")
        void findUserByIdTest2() throws Exception {
            when(userServiceImpl.findUserById(any()))
                    .thenThrow(UserNotFoundException.class);

            var response = mvc.perform(get("/users/id/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 404);
        }

        @Test
        @DisplayName("Should return http200 when find user by email.")
        void findUserByEmailTest1() throws Exception {
            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo,
                    gender, pronouns, description, socialBattery, currentSong,
                    artists, genres, bannedUsers);

            when(userServiceImpl.findUserByEmail(email))
                    .thenReturn(userReadDto);

            var response = mvc.perform(get("/users/email/pedropascal123@gmail.com")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            var expectedJson = userReadDtoJacksonTester.write(userReadDto).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http404 when find user by email.")
        void findUserByEmailTest2() throws Exception {
            when(userServiceImpl.findUserByEmail(any()))
                    .thenThrow(UserNotFoundException.class);

            var response = mvc.perform(get("/users/email/pedropascal123@gmail.com")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 404);
        }
    }

    @Nested
    @DisplayName("Tests on update users, both positive and negative cases")
    class UpdateUsersTest {

        @Test
        @DisplayName("Should return http200 when updating user by id")
        void updateUserTest1() throws Exception {
            UserUpdateDto userUpdateDto = new UserUpdateDto(id, name, password,
                    birthdate, photo, gender, pronouns, description,
                    socialBattery, currentSong);

            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo2,
                    gender, pronouns, description2, socialBattery2, currentSong2,
                    artists, genres, bannedUsers);

            when(userServiceImpl.updateUser(userUpdateDto))
                    .thenReturn(userReadDto);

            var response = mvc.perform(put("/users/id/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(userUpdateDtoJacksonTester.write(userUpdateDto).getJson()))
                    .andReturn().getResponse();

            var expectedJson = userReadDtoJacksonTester.write(userReadDto).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http404 when NOT updating user by id")
        void updateUserTest2() throws Exception {
            UserUpdateDto userUpdateDto = new UserUpdateDto(id, name, password,
                    birthdate, photo, gender, pronouns, description,
                    socialBattery, currentSong);

            when(userServiceImpl.updateUser(userUpdateDto))
                    .thenThrow(UserNotFoundException.class);

            var response = mvc.perform(put("/users/id/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(userUpdateDtoJacksonTester.write(userUpdateDto).getJson()))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 404);
        }
    }

    @Nested
    @DisplayName("Tests on toggle users, both positive and negative cases")
    class ToggleUsersTest {

        @Test
        @DisplayName("Should return http204 when toggling user to delete")
        void toggleUserTest1() throws Exception {
            Long toggleId = 1L;

            when(userServiceImpl.toggleUser(toggleId)).thenReturn(true);

            var response = mvc.perform(delete("/users/id/1"))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http200 when toggling user to reactive")
        void toggleUserTest2() throws Exception {
            Long toggleId = 1L;

            when(userServiceImpl.toggleUser(toggleId)).thenReturn(false);

            var response = mvc.perform(delete("/users/id/1"))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 204);
        }

    }

    @Nested
    @DisplayName("Tests on liked artists, both positive and negative cases")
    class LikedArtistTests {

        @Test
        @DisplayName("Should return http200 when artist is added to liked artists in a user.")
        void addLikedArtistToUserTest1() throws Exception {
            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo,
                    gender, pronouns, description, socialBattery, currentSong,
                    artists, genres, bannedUsers);

            ArrayList<ArtistDto> artistDtoList = new ArrayList<>();
            ArtistDto artistDto = new ArtistDto(1L, "Babasónicos");
            artistDtoList.add(artistDto);

            when(userServiceImpl.addLikedArtistToUser(artistDtoList, id))
                    .thenReturn(userReadDto);

            var response = mvc.perform(post("/users/likedartists/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(artistDtoJacksonTester.write(artistDtoList).getJson())
            ).andReturn().getResponse();

            var expectedJson = userReadDtoJacksonTester.write(userReadDto).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http404 when artist is added to liked artists in a user.")
        void addLikedArtistToUserTest2() throws Exception {
            ArrayList<ArtistDto> artistDtoList = new ArrayList<>();

            when(userServiceImpl.addLikedArtistToUser(any(), any()))
                    .thenThrow(EntityNotFoundException.class);

            var response = mvc.perform(post("/users/likedartists/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(artistDtoJacksonTester.write(artistDtoList).getJson())
            ).andReturn().getResponse();

            assertEquals(response.getStatus(), 404);
        }

    }

    @Nested
    @DisplayName("Tests on liked genres, both positive and negative cases")
    class LikedGenreTests {

        @Test
        @DisplayName("Should return http200 when genre is added to liked genres in a user.")
        void addLikedGenreToUserTest1() throws Exception {
            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo,
                    gender, pronouns, description, socialBattery, currentSong,
                    artists, genres, bannedUsers);

            ArrayList<GenreDto> genreDtoList = new ArrayList<>();
            GenreDto genreDto = new GenreDto(1L, "K-Pop");
            genreDtoList.add(genreDto);

            when(userServiceImpl.addLikedGenreToUser(genreDtoList, id))
                    .thenReturn(userReadDto);

            var response = mvc.perform(post("/users/likedgenres/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genreDtoJacksonTester.write(genreDtoList).getJson())
            ).andReturn().getResponse();

            var expectedJson = userReadDtoJacksonTester.write(userReadDto).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("Should return http404 when genre is added to liked genres in a user.")
        void addLikedGenreToUserTest2() throws Exception {
            ArrayList<GenreDto> genreDtoList = new ArrayList<>();

            when(userServiceImpl.addLikedGenreToUser(any(), any()))
                    .thenThrow(EntityNotFoundException.class);

            var response = mvc.perform(post("/users/likedgenres/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(genreDtoJacksonTester.write(genreDtoList).getJson())
            ).andReturn().getResponse();

            assertEquals(response.getStatus(), 404);
        }

    }

    @Nested
    @DisplayName("Tests on ban and unban users, both positive and negative cases")
    class BanUsersTest {

        @Test
        @DisplayName("Should return http200 when banning user")
        void banUserTest1() throws Exception {
            Long banningId = 1L;
            Long matchId = 50L;

            when(userServiceImpl.banUser(banningId, matchId)).thenReturn(true);

            var response = mvc.perform(delete("/users/1/ban/50"))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 204);
        }

        @Test
        @DisplayName("should not ban user")
        void banUserTest2() throws Exception {
            Long banningId = 1L;
            Long matchId = 50L;

            when(userServiceImpl.banUser(banningId, matchId)).thenReturn(false);

            var response = mvc.perform(delete("/users/1/ban/50"))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 400);
        }

        @Test
        @DisplayName("Should return http200 when unbanning user")
        void unbanUserTest1() throws Exception {
            Long banningId = 1L;
            Long bannedId = 50L;

            when(userServiceImpl.unbanUser(banningId, bannedId)).thenReturn(true);

            var response = mvc.perform(put("/users/1/unban/50"))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 200);
        }

        @Test
        @DisplayName("should not unban user")
        void unbanUserTest2() throws Exception {
            Long banningId = 1L;
            Long bannedId = 50L;

            when(userServiceImpl.banUser(banningId, bannedId)).thenReturn(false);

            var response = mvc.perform(put("/users/1/unban/50"))
                    .andReturn().getResponse();

            assertEquals(response.getStatus(), 400);
        }

        @Test
        @DisplayName("Should return http200 when finding all banned users by userid")
        void readAllBannedByUserIdTest1() throws Exception {
            UserReadDto userReadDto = new UserReadDto(id, name, birthdate, photo,
                    gender, pronouns, description, socialBattery, currentSong,
                    artists, genres, bannedUsers);
            UserReadDto userReadDto2 = new UserReadDto(id2, name2, birthdate2, photo2,
                    gender2, pronouns2, description2, socialBattery2, currentSong2,
                    artists, genres, bannedUsers);
            List<UserReadDto> bannedList = new ArrayList<>();
            bannedList.add(userReadDto2);

            when(userServiceImpl.findAllBannedByUserId(userReadDto.userId()))
                    .thenReturn(bannedList);

            var response = mvc.perform(get("/users/bannedlist/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse();

            var expectedJson = listUserReadDtoJacksonTester.
                    write(bannedList).getJson();

            assertEquals(response.getContentAsString(), expectedJson);
            assertEquals(response.getStatus(), 200);
        }
    }

}







