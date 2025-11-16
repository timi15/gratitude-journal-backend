package hu.unideb.inf.gratitude_journal.service.mapper;

import hu.unideb.inf.gratitude_journal.data.entity.GratitudeEntity;
import hu.unideb.inf.gratitude_journal.service.dto.request.GratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateGratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.GratitudeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GratitudeMapper {

    GratitudeResponseDTO toResponseDTO(GratitudeEntity gratitudeEntity);

    GratitudeEntity toEntity(GratitudeRequestDTO gratitudeRequestDTO);

    List<GratitudeResponseDTO> map(List<GratitudeEntity> gratitudeEntities);

    void updateGratitudeEntityFromDTO(
            UpdateGratitudeRequestDTO dto,
            @MappingTarget GratitudeEntity gratitudeEntity
    );

}
