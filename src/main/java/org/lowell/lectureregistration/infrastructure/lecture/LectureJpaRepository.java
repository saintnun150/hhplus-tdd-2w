package org.lowell.lectureregistration.infrastructure.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, String> {
    @Query("" +
            " select le" +
            " from LectureEntity le" +
            " where le.appliedAt =:applyDate " +
            " and le.deletedAt is not null")
    public List<LectureEntity> getLecturesWithApplyDataAndNotDeleted(LocalDateTime applyDate);
}
