package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ChatMapper {
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    @Mapping(target = "match", ignore = true)
    Chat convertCreateToChat(ChatCreateDto dto);
    ChatReadDto convertChatToRead(Chat chat);
    ChatCreateDto convertReadToCreate(ChatReadDto dto);
}
