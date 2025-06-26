package dev.timi15.gratitudejournal.controller;

import dev.timi15.gratitudejournal.dto.GratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.service.GratitudeEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/gratitudejournal")
@RequiredArgsConstructor
public class GratitudeEntryController {

    private final GratitudeEntryService gratitudeEntryService;

    @GetMapping(path = "/gratitudes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GratitudeEntryResponseDTO>> getAllGratitudeEntry() throws ExecutionException, InterruptedException {
        List<GratitudeEntryResponseDTO> gratitudeEntries = gratitudeEntryService.getAllGratitudeEntry();
        return ResponseEntity.ok(gratitudeEntries);
    }


    @GetMapping(path = "/gratitude/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GratitudeEntryResponseDTO> getGratitudeEntryById(@PathVariable String id) throws ExecutionException, InterruptedException {
        GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryService.getGratitudeEntryById(id);
        return ResponseEntity.ok(gratitudeEntry);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createGratitudeEntry(@Valid @RequestBody GratitudeEntryRequestDTO gratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        gratitudeEntryService.createGratitudeEntry(gratitudeEntryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/gratitude/{id}")
    public ResponseEntity<?> deleteGratitudeEntry(@PathVariable String id) throws ExecutionException, InterruptedException {
        gratitudeEntryService.deleteGratitudeEntryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/gratitude/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyGratitudeEntry(@PathVariable String id, @Valid @RequestBody GratitudeEntryRequestDTO gratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        gratitudeEntryService.modifyGratitudeEntryById(id, gratitudeEntryRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
