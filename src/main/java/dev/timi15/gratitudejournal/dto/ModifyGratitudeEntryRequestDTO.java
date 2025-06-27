package dev.timi15.gratitudejournal.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyGratitudeEntryRequestDTO {

    private String content;

    @PastOrPresent
    private LocalDate date;
}
