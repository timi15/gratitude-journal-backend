package dev.timi15.gratitudejournal.mapper;

import dev.timi15.gratitudejournal.dto.CreateGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.dto.ModifyGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.entity.GratitudeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {DateConverter.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GratitudeEntryMapper {


    @Mapping(target = "date", source = "gratitudeEntry.date", qualifiedByName = "timestampToLocalDate")
    GratitudeEntryResponseDTO toResponseDTO(GratitudeEntry gratitudeEntry);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "date", source = "gratitudeEntry.date", qualifiedByName = "localDateToTimestamp")
    GratitudeEntry toEntity(CreateGratitudeEntryRequestDTO gratitudeEntry);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "date", source = "gratitudeEntry.date", qualifiedByName = "localDateToTimestamp")
    GratitudeEntry toEntity(ModifyGratitudeEntryRequestDTO gratitudeEntry);




}