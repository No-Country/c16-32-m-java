package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.ReportedMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser
@WebMvcTest(ReportedMessageController.class)
class ReportedMessageControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ReportedMessageService reportedMessageService;
    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/reported-messages";
    }

    @Test
    void createReportedMessageSuccessful() throws Exception {
        //GIVEN
        ReportedMessageCreateDto in = new ReportedMessageCreateDto("Me sentí ofendido por este mensaje.", 1L);
        ReportedMessageReadDto out = new ReportedMessageReadDto(1L, null, "Me sentí ofendido por este mensaje.", Boolean.FALSE, null);

        when(this.reportedMessageService.create(any(ReportedMessageCreateDto.class))).thenReturn(out);

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
        verify(this.reportedMessageService, never()).create(any(ReportedMessageCreateDto.class));
    }
}