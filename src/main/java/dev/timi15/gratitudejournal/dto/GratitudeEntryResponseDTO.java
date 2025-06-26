package dev.timi15.gratitudejournal.dto;

import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GratitudeEntryResponseDTO {

    @DocumentId
    private String id;

    @NotNull
    private Long userId;

    @NotNull
    private String content;

    @NotNull
    @PastOrPresent
    private LocalDate date;
}
