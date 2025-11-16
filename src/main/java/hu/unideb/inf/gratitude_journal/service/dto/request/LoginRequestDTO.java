package hu.unideb.inf.gratitude_journal.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 3, max = 30, message = "Username must be 3 to 30 characters long.")
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;
}
