package hu.unideb.inf.gratitude_journal.service.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GratitudeRequestDTO {

    @NotNull(message = "Date is required.")
    @PastOrPresent(message = "Date cannot be in the future.")
    private LocalDate date;

    @Min(value = 1, message = "Mood must be at least 1.")
    @Max(value = 10, message = "Mood cannot be higher than 10.")
    @NotNull(message = "Mood is required.")
    private Integer mood;

    @NotBlank(message = "Daily highlight cannot be empty.")
    @Size(max = 255, message = "Daily highlight cannot exceed 255 characters.")
    private String dailyHighlight;

    @NotBlank(message = "Daily lesson cannot be empty.")
    @Size(max = 255, message = "Daily lesson cannot exceed 255 characters.")
    private String dailyLesson;

    @NotBlank(message = "Content cannot be empty.")
    @Size(max = 500, message = "Content cannot exceed 500 characters.")
    private String content;
}
