package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {ChatMapper.class, UserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MatchMapper {
    @Mapping(target = "chat", ignore = true)
    @Mapping(target = "matchId", ignore = true)
    @Mapping(target = "user1", ignore = true)
    @Mapping(target = "user2", ignore = true)
    Match convertCreateToMatch(MatchCreateDto dto);
    MatchReadDto convertMatchToRead(Match match);
}
