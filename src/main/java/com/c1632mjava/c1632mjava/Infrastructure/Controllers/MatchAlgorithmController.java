package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserCreateDto;
import com.c1632mjava.c1632mjava.Domain.Services.MatchAlgorithmService;
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

    @PostMapping("/{userId}")
    @Transactional
    public ResponseEntity<List<MatchReadDto>> generateMatchAlgorithm(
                                   @RequestParam Long userId){
        List<MatchReadDto> result = matchAlgorithmService.generateAlgorithm(userId);
        //Input userCreateDto y matchPreferencesCreateDto
        //output lista de matchReadDto.
        return ResponseEntity.ok(result);
    }

}
