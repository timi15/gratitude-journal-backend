package hu.unideb.inf.gratitude_journal.controller;

import hu.unideb.inf.gratitude_journal.service.UserService;
import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateUserRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/gratitude-journal/users")
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUserRole(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequestDTO updateUserRequestDTO) {

        return ResponseEntity.ok(userService.update(id, updateUserRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteById(id));
    }

}
