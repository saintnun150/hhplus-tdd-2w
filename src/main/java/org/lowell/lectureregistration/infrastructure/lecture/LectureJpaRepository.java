package org.lowell.lectureregistration.infrastructure.lecture;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, String> {
    @Query("" +
            " select le" +
            " from LectureEntity le" +
            " where le.appliedAt =:applyDate " +
            " and le.deletedAt is null")
    List<LectureEntity> getLecturesWithApplyDataAndNotDeleted(LocalDateTime applyDate);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("" +
            " select le" +
            " from LectureEntity le " +
            " where le.lectureId =:lectureId" +
            "   and le.deletedAt is null")
    LectureEntity findByIdWithLock(String lectureId);

}
