package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MatchAlgorithmService {

    List<MatchReadDto> generateAlgorithm(Long userId) throws JsonProcessingException;

}
