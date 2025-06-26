package dev.timi15.gratitudejournal.dto;

import jakarta.validation.constraints.NotNull;
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
public class GratitudeEntryRequestDTO {

    @NotNull
    private String content;

    @PastOrPresent
    private LocalDate date;

}
