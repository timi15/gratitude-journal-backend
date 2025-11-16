package hu.unideb.inf.gratitude_journal.service.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateGratitudeRequestDTO {

    @Min(value = 1, message = "Mood must be at least 1.")
    @Max(value = 10, message = "Mood cannot be more than 10.")
    private Integer mood;

    @Size(max = 255, message = "Daily highlight cannot exceed 255 characters.")
    private String dailyHighlight;

    @Size(max = 255, message = "Daily lesson cannot exceed 255 characters.")
    private String dailyLesson;

    @Size(max = 500, message = "Content cannot exceed 500 characters.")
    private String content;
}
