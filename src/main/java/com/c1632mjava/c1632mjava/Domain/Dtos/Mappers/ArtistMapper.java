package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ArtistDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
        ArtistDto convertArtistToDto(Artist artist);
        Artist convertDtoToArtist(ArtistDto artistDto);
        List<ArtistDto> convertArtistListToDtoList(List<Artist> artist); //This mapper is being used by userMapper.
     }
