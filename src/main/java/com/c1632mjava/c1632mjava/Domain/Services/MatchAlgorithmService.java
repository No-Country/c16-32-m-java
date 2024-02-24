package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import java.util.List;

public interface MatchAlgorithmService {

    List<MatchReadDto> generateAlgorithm(Long userId);

}
