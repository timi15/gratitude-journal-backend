package hu.unideb.inf.gratitude_journal.service.impl;

import hu.unideb.inf.gratitude_journal.data.entity.GratitudeEntity;
import hu.unideb.inf.gratitude_journal.data.entity.UserEntity;
import hu.unideb.inf.gratitude_journal.exception.ForbiddenOperationException;
import hu.unideb.inf.gratitude_journal.exception.NotFoundException;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateGratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.mapper.GratitudeMapper;
import hu.unideb.inf.gratitude_journal.data.repository.GratitudeRepository;
import hu.unideb.inf.gratitude_journal.service.GratitudeService;
import hu.unideb.inf.gratitude_journal.service.dto.request.GratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.GratitudeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GratitudeServiceImpl implements GratitudeService {

    private final GratitudeRepository gratitudeRepository;
    private final GratitudeMapper gratitudeMapper;

    private UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private GratitudeEntity getGratitudeOrThrow(Long id) {
        return gratitudeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Gratitude post not found with id: " + id));
    }

    private void checkOwner(GratitudeEntity entity, UserEntity currentUser) {
        if (!entity.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenOperationException("You do not have permission to access this entry.");
        }
    }


    @Override
    public GratitudeResponseDTO save(GratitudeRequestDTO dto) {
        UserEntity currentUser = getCurrentUser();

        GratitudeEntity entity = gratitudeMapper.toEntity(dto);
        entity.setUser(currentUser);

        gratitudeRepository.save(entity);
        return gratitudeMapper.toResponseDTO(entity);
    }

    @Override
    public List<GratitudeResponseDTO> findAll() {
        UserEntity currentUser = getCurrentUser();

        return gratitudeMapper.map(gratitudeRepository.findAllByUserId(currentUser.getId()));
    }

    @Override
    public GratitudeResponseDTO findById(Long id) {
        UserEntity currentUser = getCurrentUser();
        GratitudeEntity entity = getGratitudeOrThrow(id);

        checkOwner(entity, currentUser);

        return gratitudeMapper.toResponseDTO(entity);
    }

    @Override
    public GratitudeResponseDTO update(Long id, UpdateGratitudeRequestDTO dto) {
        UserEntity currentUser = getCurrentUser();
        GratitudeEntity entity = getGratitudeOrThrow(id);

        checkOwner(entity, currentUser);

        gratitudeMapper.updateGratitudeEntityFromDTO(dto, entity);
        gratitudeRepository.save(entity);

        return gratitudeMapper.toResponseDTO(entity);
    }

    @Override
    public GratitudeResponseDTO deleteById(Long id) {
        UserEntity currentUser = getCurrentUser();
        GratitudeEntity entity = getGratitudeOrThrow(id);

        checkOwner(entity, currentUser);

        gratitudeRepository.delete(entity);

        return gratitudeMapper.toResponseDTO(entity);
    }
}
