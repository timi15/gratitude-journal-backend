package hu.unideb.inf.gratitude_journal.service.impl;

import hu.unideb.inf.gratitude_journal.data.entity.UserEntity;
import hu.unideb.inf.gratitude_journal.enums.Role;
import hu.unideb.inf.gratitude_journal.exception.EmailAlreadyUsedException;
import hu.unideb.inf.gratitude_journal.exception.NotFoundException;
import hu.unideb.inf.gratitude_journal.exception.UsernameAlreadyExistException;
import hu.unideb.inf.gratitude_journal.service.mapper.UserMapper;
import hu.unideb.inf.gratitude_journal.data.repository.UserRepository;
import hu.unideb.inf.gratitude_journal.service.AuthenticationService;
import hu.unideb.inf.gratitude_journal.service.TokenService;
import hu.unideb.inf.gratitude_journal.service.dto.request.LoginRequestDTO;
import hu.unideb.inf.gratitude_journal.service.dto.request.RegistrationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public void registration(RegistrationRequestDTO registrationRequestDTO) {

        if (userRepository.existsByUsername(registrationRequestDTO.getUsername())) {
            throw new UsernameAlreadyExistException("Username is already taken.");
        }

        if (userRepository.existsByEmail(registrationRequestDTO.getEmail())) {
            throw new EmailAlreadyUsedException("Email is already taken.");
        }

        UserEntity entity = userMapper.registrationDTOtoEntity(registrationRequestDTO);
        entity.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));

        entity.setRole(Role.USER);

        userRepository.save(entity);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {

        if (!userRepository.existsByUsername(loginRequestDTO.getUsername())) {
            throw new NotFoundException("Invalid username or password.");
        }

        SecurityContext context =
                SecurityContextHolder.createEmptyContext();

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        var user = userRepository.findByUsername(loginRequestDTO.getUsername());
        return tokenService.generateToken(user);
    }
}
