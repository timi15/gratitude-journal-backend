package hu.unideb.inf.gratitude_journal.service.dto.response;

import hu.unideb.inf.gratitude_journal.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String name;

    private String username;

    private String email;

    private Role role;
}
