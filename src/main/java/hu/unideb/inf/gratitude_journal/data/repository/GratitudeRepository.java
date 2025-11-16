package hu.unideb.inf.gratitude_journal.data.repository;

import hu.unideb.inf.gratitude_journal.data.entity.GratitudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GratitudeRepository extends JpaRepository<GratitudeEntity, Long> {

    List<GratitudeEntity> findAllByUserId(Long userId);

}
