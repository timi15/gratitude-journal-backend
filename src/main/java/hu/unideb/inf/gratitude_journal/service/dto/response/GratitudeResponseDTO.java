package hu.unideb.inf.gratitude_journal.service.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GratitudeResponseDTO {

    private Long id;

    private LocalDate date;

    private Integer mood;

    private String dailyHighlight;

    private String dailyLesson;

    private String content;
}
