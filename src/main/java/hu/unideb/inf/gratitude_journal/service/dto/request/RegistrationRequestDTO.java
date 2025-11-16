package hu.unideb.inf.gratitude_journal.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequestDTO {

    @NotBlank(message = "Name cannot be empty.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 3, max = 30, message = "Username must be 3 to 30 characters long.")
    private String username;

    @Email(message = "Please provide a valid email address.")
    @NotBlank(message = "Email cannot be empty.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;
}
