package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class MatchControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MatchService matchService;

    private ObjectMapper objectMapper;
    private String url;
    private Float compatibilityPercentage;
    private UserReadDto user1;
    private UserReadDto user2;
    private Long matchId;
    private LocalDateTime dateOfMatch;
    private ChatReadDto chat;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/matches";
        this.compatibilityPercentage = 78.9F;
        this.matchId = 1L;
        this.dateOfMatch = null;
        this.chat = null;
        this.user1 = null;
        this.user2 = null;
    }

    @Test
    void findMatchById() throws Exception {
        //GIVEN
        Long matchId = this.matchId;
        MatchReadDto out = new MatchReadDto(this.matchId, this.compatibilityPercentage, this.dateOfMatch, this.user1, this.user2, this.chat);

        when(this.matchService.findMatchById(anyLong())).thenReturn(out);

        //WHEN
        this.mvc.perform(get(this.url.concat("/{id}"), matchId)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(out)));

        verify(this.matchService, times(1)).findMatchById(anyLong());
    }

    @Test
    void findAllMatchesByUserId() throws Exception {
        //GIVEN
        Long userId = 1L;
        List<MatchReadDto> matches = Arrays.asList(
                new MatchReadDto(1L, this.compatibilityPercentage, this.dateOfMatch, this.user1, this.user2, this.chat),
                new MatchReadDto(2L, this.compatibilityPercentage, this.dateOfMatch, this.user1, this.user2, this.chat)
        );
        Page<MatchReadDto> out = new PageImpl<>(matches);

        when(this.matchService.findAllMatchesByUserId(anyLong(), any(Pageable.class))).thenReturn(out);

        //WHEN
        this.mvc.perform(get(this.url.concat("/users/{userId}"), userId)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.matchService, times(1)).findAllMatchesByUserId(anyLong(), any(Pageable.class));
    }

    @Test
    void deleteMatch() throws Exception {
        //GIVEN

        Long matchId = this.matchId;

        doNothing().when(this.matchService).deleteMatch(anyLong());

        //WHEN
        this.mvc.perform(delete(this.url.concat("/{matchId}"), matchId)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isNoContent());

        verify(this.matchService, times(1)).deleteMatch(anyLong());
    }
}