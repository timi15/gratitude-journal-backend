package hu.unideb.inf.gratitude_journal.service;

import hu.unideb.inf.gratitude_journal.service.dto.request.GratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateGratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.GratitudeResponseDTO;

import java.util.List;

public interface GratitudeService {

    GratitudeResponseDTO save(GratitudeRequestDTO gratitude);

    List<GratitudeResponseDTO> findAll();

    GratitudeResponseDTO findById(Long id);

    GratitudeResponseDTO update(Long id, UpdateGratitudeRequestDTO updateGratitudeRequestDTO);

    GratitudeResponseDTO deleteById(Long id);
}
