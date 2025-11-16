package hu.unideb.inf.gratitude_journal.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gratitude_posts")
public class GratitudeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer mood;

    @Column(name = "daily_highlight", nullable = false)
    private String dailyHighlight;

    @Column(name = "daily_lesson", nullable = false)
    private String dailyLesson;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
