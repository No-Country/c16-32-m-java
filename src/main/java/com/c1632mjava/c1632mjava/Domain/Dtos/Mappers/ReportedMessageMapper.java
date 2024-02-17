package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.ReportedMessage;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {ChatMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReportedMessageMapper {
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    @Mapping(target = "chat", ignore = true)
    ReportedMessage convertCreateToReported(ReportedMessageCreateDto dto);
    ReportedMessageReadDto convertReportedToRead(ReportedMessage reportedMessage);
}
