package hu.unideb.inf.gratitude_journal.controller;

import hu.unideb.inf.gratitude_journal.service.AuthenticationService;
import hu.unideb.inf.gratitude_journal.service.dto.request.LoginRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.RegistrationRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/gratitude-journal/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid RegistrationRequestDTO registrationRequestDTO) {
        authenticationService.registration(registrationRequestDTO);
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return authenticationService.login(loginRequestDTO);
    }
}
