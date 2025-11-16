package hu.unideb.inf.gratitude_journal.service.dto.request;

import hu.unideb.inf.gratitude_journal.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateUserRequestDTO {

    private Role role;

}
