package dev.timi15.gratitudejournal.mapper;

import dev.timi15.gratitudejournal.dto.GratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.entity.GratitudeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateConverter.class})
public interface GratitudeEntryMapper {


    @Mapping(target = "date", source = "gratitudeEntry.date", qualifiedByName = "timestampToLocalDate")
    GratitudeEntryResponseDTO toResponseDTO(GratitudeEntry gratitudeEntry);


    @Mapping(target = "date", source = "gratitudeEntry.date", qualifiedByName = "localDateToTimestamp")
    GratitudeEntry toEntity(GratitudeEntryRequestDTO gratitudeEntry);


}

