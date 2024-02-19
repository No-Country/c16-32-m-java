package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WithMockUser
@WebMvcTest(MatchController.class)
class MatchControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private MatchService matchService;
    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/matches";
    }

    @Test
    void findMatchById() throws Exception {
        //GIVEN
        Long matchId = 1L;
        MatchReadDto out = new MatchReadDto(1L, 98.9F, null, null, null, null);

        when(this.matchService.findMatchById(anyLong())).thenReturn(out);

        //WHEN
        this.mvc.perform(get(this.url.concat("/{id}"), matchId)
                .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(out)));
        verify(this.matchService).findMatchById(anyLong());
    }

    @Test
    void findAllMatchesByUserId() throws Exception {
        //GIVEN
        Long userId = 1L;
        List<MatchReadDto> matches = Arrays.asList(
                new MatchReadDto(1L, 98.9F, null, null, null, null),
                new MatchReadDto(2L, 68.9F, null, null, null, null)
        );
        Page<MatchReadDto> out = new PageImpl<>(matches);

        when(this.matchService.findAllMatchesByUserId(anyLong(), any(Pageable.class))).thenReturn(out);

        //WHEN
        this.mvc.perform(get(this.url.concat("/users/{userId}"), userId)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.matchService).findAllMatchesByUserId(anyLong(), any(Pageable.class));
    }

    @Test
    void deleteMatch() throws Exception {
        //GIVEN
        Long matchId = 1L;

        doNothing().when(this.matchService).deleteMatch(anyLong());

        //WHEN
        this.mvc.perform(delete(this.url.concat("/{matchId}"), matchId)
                .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isNoContent());
        verify(this.matchService).deleteMatch(anyLong());
    }
}