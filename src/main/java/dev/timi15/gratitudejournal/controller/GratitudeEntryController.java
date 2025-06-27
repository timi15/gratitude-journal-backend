package dev.timi15.gratitudejournal.controller;

import dev.timi15.gratitudejournal.dto.CreateGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.dto.ModifyGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.service.GratitudeEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gratitude Entry Management", description = "APIs for managing gratitude entries")
@RequiredArgsConstructor
public class GratitudeEntryController {

    private final GratitudeEntryService gratitudeEntryService;

    @Operation(summary = "Get all gratitude entries", description = "Retrieve a list of all gratitude entry in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gratitude entries retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GratitudeEntryResponseDTO.class)))
    })
    @GetMapping(path = "/gratitudes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GratitudeEntryResponseDTO>> getAllGratitudeEntry() throws ExecutionException, InterruptedException {
        List<GratitudeEntryResponseDTO> gratitudeEntries = gratitudeEntryService.getAllGratitudeEntry();
        return ResponseEntity.ok(gratitudeEntries);
    }

    @Operation(summary = "Get gratitude entry by ID", description = "Retrieve a gratitude entry's details using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gratitude entry found",
                    content = @Content(schema = @Schema(implementation = GratitudeEntryResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gratitude entry found not found",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(path = "/gratitude/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GratitudeEntryResponseDTO> getGratitudeEntryById(@PathVariable String id) throws ExecutionException, InterruptedException {
        GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryService.getGratitudeEntryById(id);
        return ResponseEntity.ok(gratitudeEntry);
    }

    @Operation(summary = "Create a new gratitude entry", description = "Add a new gratitude entry to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gratitude entry created successfully",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @ResponseBody
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GratitudeEntryResponseDTO> createGratitudeEntry(@Valid @RequestBody CreateGratitudeEntryRequestDTO createGratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryService.createGratitudeEntry(createGratitudeEntryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(gratitudeEntry);
    }


    @Operation(summary = "Update a gratitude entry", description = "Update an existing gratitude entry's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gratitude entry updated successfully",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Gratitude entry not found",
                    content = @Content(schema = @Schema()))
    })
    @ResponseBody
    @PutMapping(path = "/gratitude/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GratitudeEntryResponseDTO> modifyGratitudeEntry(@PathVariable String id, @Valid @RequestBody ModifyGratitudeEntryRequestDTO modifyGratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryService.modifyGratitudeEntryById(id, modifyGratitudeEntryRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(gratitudeEntry);
    }

    @Operation(summary = "Delete a gratitude entry", description = "Delete a gratitude entry from the system using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Gratitude entry deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Gratitude entry not found",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/gratitude/{id}")
    public ResponseEntity<?> deleteGratitudeEntry(@PathVariable String id) throws ExecutionException, InterruptedException {
        gratitudeEntryService.deleteGratitudeEntryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
