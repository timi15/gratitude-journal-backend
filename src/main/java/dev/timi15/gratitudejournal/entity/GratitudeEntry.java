package dev.timi15.gratitudejournal.entity;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GratitudeEntry {

    @DocumentId
    private String id;
    private Long userId;
    private String content;
    private Timestamp date;
}
