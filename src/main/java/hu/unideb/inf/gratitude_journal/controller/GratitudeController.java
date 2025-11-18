package hu.unideb.inf.gratitude_journal.controller;

import hu.unideb.inf.gratitude_journal.service.GratitudeService;
import hu.unideb.inf.gratitude_journal.service.dto.request.GratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateGratitudeRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.GratitudeResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/gratitude-journal/gratitude")
@RequiredArgsConstructor
@Validated
public class GratitudeController {

    private final GratitudeService gratitudeService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<GratitudeResponseDTO>> getAllPosts() {
        return ResponseEntity.ok(gratitudeService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<GratitudeResponseDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(gratitudeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<GratitudeResponseDTO> savePost(@RequestBody @Valid GratitudeRequestDTO gratitudeRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gratitudeService.save(gratitudeRequestDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<GratitudeResponseDTO> updatePost(@PathVariable Long id, @RequestBody @Valid UpdateGratitudeRequestDTO updateGratitudeRequestDTO) {
        return ResponseEntity.ok(gratitudeService.update(id, updateGratitudeRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<GratitudeResponseDTO> deletePost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gratitudeService.deleteById(id));
    }

}
