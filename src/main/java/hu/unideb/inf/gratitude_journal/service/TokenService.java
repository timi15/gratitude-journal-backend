package hu.unideb.inf.gratitude_journal.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String generateToken(UserDetails user);

    String extractUsername(String token);

    Boolean validateToken(String token, UserDetails user);

}
