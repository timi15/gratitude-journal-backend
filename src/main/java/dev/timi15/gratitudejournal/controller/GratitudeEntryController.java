package dev.timi15.gratitudejournal.controller;

import dev.timi15.gratitudejournal.dto.GratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.service.GratitudeEntryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/gratitudejournal")
@RequiredArgsConstructor
public class GratitudeEntryController {

    private final GratitudeEntryServiceImpl gratitudeEntryServiceImpl;

    @GetMapping("/gratitudes")
    public ResponseEntity<List<GratitudeEntryResponseDTO>> getAllGratitudeEntry() throws ExecutionException, InterruptedException {
        List<GratitudeEntryResponseDTO> gratitudeEntries = gratitudeEntryServiceImpl.getAllGratitudeEntry();
        return ResponseEntity.ok(gratitudeEntries);
    }


    @GetMapping("/gratitude/{id}")
    public ResponseEntity<GratitudeEntryResponseDTO> getGratitudeEntryById(@PathVariable String id) throws ExecutionException, InterruptedException {
        GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryServiceImpl.getGratitudeEntryById(id);
        return ResponseEntity.ok(gratitudeEntry);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createGratitudeEntry(@RequestBody GratitudeEntryRequestDTO gratitudeEntryRequestDTO){
        gratitudeEntryServiceImpl.createGratitudeEntry(gratitudeEntryRequestDTO);
        return ResponseEntity.ok().build();
    }

}
