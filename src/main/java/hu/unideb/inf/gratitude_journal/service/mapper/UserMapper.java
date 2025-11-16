package hu.unideb.inf.gratitude_journal.service.mapper;

import hu.unideb.inf.gratitude_journal.data.entity.UserEntity;
import hu.unideb.inf.gratitude_journal.service.dto.request.RegistrationRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateUserRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserEntity registrationDTOtoEntity(RegistrationRequestDTO registrationRequestDTO);

    UserResponseDTO toResponseDTO(UserEntity userEntity);

    List<UserResponseDTO> map(List<UserEntity> userEntities);


    void updateUserEntityFromDTO(UpdateUserRequestDTO dto, @MappingTarget UserEntity userEntity);

}
