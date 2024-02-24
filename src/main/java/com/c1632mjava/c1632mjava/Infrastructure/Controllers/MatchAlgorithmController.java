package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.MatchPreferencesCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserCreateDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/generatematches")
@RequiredArgsConstructor
public class MatchAlgorithmController {

    @PostMapping("/")
    @Transactional
    public ResponseEntity<List<MatchReadDto>> generateMatchAlgorithm(@RequestBody @Valid
              MatchPreferencesCreateDto matchPreferencesCreateDto,
              @RequestBody @Valid UserCreateDto userCreateDto){
        //Input userCreateDto y matchPreferencesCreateDto
        //output lista de matchReadDto.
        List<MatchReadDto> result = new ArrayList<>();
        return ResponseEntity.ok(result);
    }

}
