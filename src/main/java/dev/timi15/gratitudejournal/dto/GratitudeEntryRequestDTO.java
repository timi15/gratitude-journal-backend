package dev.timi15.gratitudejournal.dto;

import com.google.cloud.firestore.annotation.DocumentId;
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

    @DocumentId
    private String id;
    private Long userId;
    private String content;
    private LocalDate date;

}
