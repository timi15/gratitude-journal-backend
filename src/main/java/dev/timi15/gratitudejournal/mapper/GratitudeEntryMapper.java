package dev.timi15.gratitudejournal.mapper;

import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.entity.GratitudeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface GratitudeEntryMapper {

    GratitudeEntryMapper INSTANCE = Mappers.getMapper(GratitudeEntryMapper.class);

    @Mapping(target = "date", source = "gratitudeEntry.date", qualifiedByName = "timestampToLocalDate")
    GratitudeEntryResponseDTO toResponseDTO(GratitudeEntry gratitudeEntry);

    //GratitudeEntry toEntity(GratitudeEntryResponseDTO gratitudeEntryResponseDTO);

    @Named("timestampToLocalDate")
    static LocalDate convertTimestamp(com.google.cloud.Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

