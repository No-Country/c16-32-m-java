package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserCreateDto;
import com.c1632mjava.c1632mjava.Domain.Services.MatchAlgorithmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/generatematches")
@RequiredArgsConstructor
public class MatchAlgorithmController {

    public final MatchAlgorithmService matchAlgorithmService;

    @GetMapping("/{userId}")
    @Transactional
    public ResponseEntity<List<MatchReadDto>> generateMatchAlgorithm(
                                   @PathVariable Long userId) throws JsonProcessingException {
        List<MatchReadDto> result = matchAlgorithmService.generateAlgorithm(userId);
        return ResponseEntity.ok(result);
    }

}
