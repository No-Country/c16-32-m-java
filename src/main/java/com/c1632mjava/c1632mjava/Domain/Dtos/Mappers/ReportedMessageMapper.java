package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.ReportedMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportedMessageMapper {
    ReportedMessage convertCreateToReported(ReportedMessageCreateDto dto);
    ReportedMessageReadDto convertReportedToRead(ReportedMessage reportedMessage);
}
