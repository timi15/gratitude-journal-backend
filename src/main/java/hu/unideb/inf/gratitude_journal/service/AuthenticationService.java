package hu.unideb.inf.gratitude_journal.service;

import hu.unideb.inf.gratitude_journal.service.dto.request.LoginRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.RegistrationRequestDTO;

public interface AuthenticationService {

    void registration(RegistrationRequestDTO registrationRequestDTO);

    String login(LoginRequestDTO loginRequestDTO);

}
