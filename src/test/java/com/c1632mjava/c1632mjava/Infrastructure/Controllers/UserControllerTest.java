package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Application.Implementations.UserServiceImpl;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest //Test de SpringBoot porque Controller.
@AutoConfigureMockMvc //Configura el manejo de endpoints (post, put, get, delete, patch).
@WithMockUser //Habilita la autenticación (evita el error 403)
@AutoConfigureJsonTesters //Permite manejarse con DTO en lugar de requerir JSON.
class UserControllerTest {

    @Autowired //Como el test está fuera del contexto de SpringBoot no pueden hacerse por constructor.
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserCreateDto> userCreateDtoJacksonTester;
    @Autowired
    private JacksonTester<UserReadDto> userReadDtoJacksonTester;
    @Autowired
    private JacksonTester<UserUpdateDto> userUpdateDtoJacksonTester;

    @MockBean //Suplanta el service y evita hacer la llamada a base de datos original.
    private UserServiceImpl userServiceImpl;

    @Test
    @DisplayName("Should return http200 when provided data is valid.")
    void registerUserTest1() throws Exception {

        var name = "Pedro Pascal";
        var password = "Pedro123!";
        var email = "pedropascal123@gmail.com";
        var birthdate = LocalDateTime.of(1980, Month.JULY, 23, 00, 00);
        var photo = "string_photo.jpg";
        var gender = Gender.MASCULINO;
        var pronouns = "él";
        var description = "Hace falta? jajaja";
        var socialBattery = SocialBattery.AVAILABLE;

        var readUser = new UserReadDto(1L, name, birthdate, photo, gender, pronouns, description, socialBattery, null, null, null, null);

        when(userServiceImpl.registerUser(any())).thenReturn(readUser);

        var response = mvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userCreateDtoJacksonTester.write(new UserCreateDto(name, password,
                        email, birthdate, photo, gender, pronouns, description)).getJson())
        ).andReturn().getResponse();

        var expectedJson = userReadDtoJacksonTester.write(readUser).getJson();

        assertEquals(response.getContentAsString(), expectedJson);
    }

    @Test
    @DisplayName("Should return http 400, i.e. post without Json body.")
    void registerUserTest2() throws Exception {
        var response = mvc.perform(post("/register")).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }
}
