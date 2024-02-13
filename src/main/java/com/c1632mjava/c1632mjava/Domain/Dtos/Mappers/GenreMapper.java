package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.GenreDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
        GenreDto convertGenreToDto(Genre genre);
        Genre convertDtoToGenre(GenreDto genreDto);
        List<GenreDto> convertGenreListToDtoList(List<Genre> genre); //This mapper is being used by userMapper.
     }
