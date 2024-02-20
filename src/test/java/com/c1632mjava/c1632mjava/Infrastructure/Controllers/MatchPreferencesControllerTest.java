package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.C1632MJavaApplication;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesUpdateDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.content;

import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
//@ContextConfiguration(classes= C1632MJavaApplication.class)
//@WebMvcTest(MatchPreferencesController.class)
class MatchPreferencesControllerTest {
    /*data for testing*/
    Long matchPreferenceId=20L;
    Long userId=8L;
    boolean female=false;
    boolean male=false;
    boolean other=true;
    int minAge=18;
    int maxAge=50;
    Distance distance=Distance.MUY_CERCA;
    CompatibilityPercentage compatibilityPercentage=CompatibilityPercentage.NOTAS_SIMILARES;
    boolean longTermRelationship=true;
    boolean justFriends=false;
    boolean rightNow=true;
    boolean active=true;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MatchPreferencesService matchPreferencesService;
    private MatchPreferencesController matchPreferencesController;
    ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        objectMapper=new ObjectMapper();
        matchPreferencesController=new MatchPreferencesController(matchPreferencesService);

    }
    @Test
    void createMatchPreferences() throws Exception {
        //Mockito.when(matchPreferencesService.createMatchPreferences(any(MatchPreferencesCreateDto.class)))
          //      .thenReturn(matchPreferencesReadDto);
        /*usar jacksontester para mockear el json*/
        MatchPreferencesCreateDto matchPreferencesCreateDto=new MatchPreferencesCreateDto(userId, female, male, other,
                minAge, maxAge, distance, compatibilityPercentage, longTermRelationship, justFriends, rightNow);
        MatchPreferencesReadDto matchPreferencesReadDto=new MatchPreferencesReadDto(female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship,justFriends, rightNow);

        when(matchPreferencesService.createMatchPreferences(any(MatchPreferencesCreateDto.class)))
                .thenReturn(matchPreferencesReadDto);
        mvc.perform(post("/preferences/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matchPreferencesCreateDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.female").value(matchPreferencesReadDto.female()))
                .andExpect(jsonPath("$.male").value(matchPreferencesReadDto.male()))
                .andExpect(jsonPath("$.other").value(matchPreferencesReadDto.other()))
                .andExpect(jsonPath("$.minAge").value(matchPreferencesReadDto.minAge()))
                .andExpect(jsonPath("$.maxAge").value(matchPreferencesReadDto.maxAge()))
                .andExpect(jsonPath("$.distance").value(matchPreferencesReadDto.distance().name()))
                .andExpect(jsonPath("$.compatibilityPercentage").value(matchPreferencesReadDto.compatibilityPercentage().name()))
                .andExpect(jsonPath("$.longTermRelationship").value(matchPreferencesReadDto.longTermRelationship()))
                .andExpect(jsonPath("$.justFriends").value(matchPreferencesReadDto.justFriends()))
                .andExpect(jsonPath("$.rightNow").value(matchPreferencesReadDto.rightNow()));
    }

    @Test
    void findByUserId() throws Exception {
        MatchPreferencesReadDto matchPreferencesReadDto=new MatchPreferencesReadDto(female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship,justFriends, rightNow);

        when(matchPreferencesService.findPreferencesByUserId(userId))
                .thenReturn(new MatchPreferencesReadDto(female, male, other, minAge, maxAge,
                        distance, compatibilityPercentage, longTermRelationship,justFriends, rightNow));

        mvc.perform(get("/preferences/id/8").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.female").value(matchPreferencesReadDto.female()))
                .andExpect(jsonPath("$.male").value(matchPreferencesReadDto.male()))
                .andExpect(jsonPath("$.other").value(matchPreferencesReadDto.other()))
                .andExpect(jsonPath("$.minAge").value(matchPreferencesReadDto.minAge()))
                .andExpect(jsonPath("$.maxAge").value(matchPreferencesReadDto.maxAge()))
                .andExpect(jsonPath("$.distance").value(matchPreferencesReadDto.distance().name()))
                .andExpect(jsonPath("$.compatibilityPercentage").value(matchPreferencesReadDto.compatibilityPercentage().name()))
                .andExpect(jsonPath("$.longTermRelationship").value(matchPreferencesReadDto.longTermRelationship()))
                .andExpect(jsonPath("$.justFriends").value(matchPreferencesReadDto.justFriends()))
                .andExpect(jsonPath("$.rightNow").value(matchPreferencesReadDto.rightNow()));

        //verify(matchPreferencesService.findPreferencesByUserId(userId));
    }

    @Test
    void updateMatchPreferences() throws Exception {
        MatchPreferencesReadDto matchPreferencesReadDto=new MatchPreferencesReadDto(female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship,justFriends, rightNow);

        boolean female=false;
        boolean male=true;
        boolean other=false;
        int minAge=22;
        int maxAge=28;

        MatchPreferencesUpdateDto matchPreferencesUpdateDto=new MatchPreferencesUpdateDto(userId,female, male, other, minAge, maxAge,
                distance, compatibilityPercentage, longTermRelationship,justFriends, rightNow);

        when(matchPreferencesService.updateMatchPreferences(any(MatchPreferencesUpdateDto.class)))
                .thenReturn(matchPreferencesReadDto);

        String updateContent = objectMapper.writeValueAsString(matchPreferencesUpdateDto);

        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.put("/preferences")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateContent);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.female").value(matchPreferencesReadDto.female()))
                .andExpect(jsonPath("$.male").value(matchPreferencesReadDto.male()))
                .andExpect(jsonPath("$.other").value(matchPreferencesReadDto.other()))
                .andExpect(jsonPath("$.minAge").value(matchPreferencesReadDto.minAge()))
                .andExpect(jsonPath("$.maxAge").value(matchPreferencesReadDto.maxAge()))
                .andExpect(jsonPath("$.distance").value(matchPreferencesReadDto.distance().name()))
                .andExpect(jsonPath("$.compatibilityPercentage").value(matchPreferencesReadDto.compatibilityPercentage().name()))
                .andExpect(jsonPath("$.longTermRelationship").value(matchPreferencesReadDto.longTermRelationship()))
                .andExpect(jsonPath("$.justFriends").value(matchPreferencesReadDto.justFriends()))
                .andExpect(jsonPath("$.rightNow").value(matchPreferencesReadDto.rightNow()));
        verify(matchPreferencesService).updateMatchPreferences(matchPreferencesUpdateDto);
    }


    @Test
    void toggleMatchPreferences() {
        boolean toggleResult = true;

        when(matchPreferencesService.toggleMatchPreferences(userId)).thenReturn(toggleResult);
        ResponseEntity<Boolean> responseEntity = matchPreferencesController.toggleMatchPreferences(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(matchPreferencesService, times(1)).toggleMatchPreferences(userId);
    }
    @Test
    void falseToggleMatchPreferences() {
        boolean toggleResult = false;

        when(matchPreferencesService.toggleMatchPreferences(userId)).thenReturn(toggleResult);
        ResponseEntity<Boolean> responseEntity = matchPreferencesController.toggleMatchPreferences(userId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(matchPreferencesService, times(1)).toggleMatchPreferences(userId);
    }
}