package dev.timi15.gratitudejournal.dto;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GratitudeEntryResponseDTO {

    @DocumentId
    private String id;
    private Long userId;
    private String content;
    private LocalDate date;
}
