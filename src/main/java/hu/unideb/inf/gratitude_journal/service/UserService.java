package hu.unideb.inf.gratitude_journal.service;

import hu.unideb.inf.gratitude_journal.service.dto.request.UpdateUserRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.response.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService getUserDetailsService();

    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Long id);

    UserResponseDTO deleteById(Long id);

    UserResponseDTO update(Long id, UpdateUserRequestDTO updateUserRequestDTO);
}
