package dev.timi15.gratitudejournal.service;

import dev.timi15.gratitudejournal.dto.GratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GratitudeEntryService {

    List<GratitudeEntryResponseDTO> getAllGratitudeEntry() throws ExecutionException, InterruptedException;

    GratitudeEntryResponseDTO getGratitudeEntryById(String id) throws ExecutionException, InterruptedException;

    void createGratitudeEntry(GratitudeEntryRequestDTO gratitudeEntryRequestDTO) throws ExecutionException, InterruptedException;

}
