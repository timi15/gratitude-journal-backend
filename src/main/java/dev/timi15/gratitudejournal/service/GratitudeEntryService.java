package dev.timi15.gratitudejournal.service;

import dev.timi15.gratitudejournal.dto.CreateGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.dto.ModifyGratitudeEntryRequestDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GratitudeEntryService {

    List<GratitudeEntryResponseDTO> getAllGratitudeEntry() throws ExecutionException, InterruptedException;

    GratitudeEntryResponseDTO getGratitudeEntryById(String id) throws ExecutionException, InterruptedException;

    GratitudeEntryResponseDTO createGratitudeEntry(CreateGratitudeEntryRequestDTO createGratitudeEntryRequestDTO) throws ExecutionException, InterruptedException;

    void deleteGratitudeEntryById(String id) throws ExecutionException, InterruptedException;

    GratitudeEntryResponseDTO modifyGratitudeEntryById(String id, ModifyGratitudeEntryRequestDTO modifyGratitudeEntryRequestDTO) throws ExecutionException, InterruptedException;

}
