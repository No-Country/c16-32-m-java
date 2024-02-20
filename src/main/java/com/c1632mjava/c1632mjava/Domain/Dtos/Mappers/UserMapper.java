package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, GenreMapper.class})
public interface UserMapper {
    @Mapping(target = "Artists", source = "artists")
    @Mapping(target = "Genres", source = "genres")
    UserReadDto convertUserToRead(User user);
    User convertCreateToUser(UserCreateDto userCreateDto);
    User convertReadToUser(UserReadDto userReadDto);
     }
