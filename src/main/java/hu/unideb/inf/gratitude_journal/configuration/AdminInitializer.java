package hu.unideb.inf.gratitude_journal.configuration;

import hu.unideb.inf.gratitude_journal.data.entity.UserEntity;
import hu.unideb.inf.gratitude_journal.data.repository.UserRepository;
import hu.unideb.inf.gratitude_journal.enums.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_PASSWORD}")
    private String password;

    @PostConstruct
    public void init() {

        if (!userRepository.existsByUsername("admin")) {

            UserEntity admin = UserEntity.builder()
                    .name("admin")
                    .username("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode(password))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            System.out.println(">>> Admin user CREATED");
        } else {
            System.out.println(">>> Admin user already exists");
        }
    }

}
