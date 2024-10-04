package org.lowell.lectureregistration.infrastructure.lectureRegistration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistrationEntity, Long> {
    LectureRegistrationEntity findByLectureIdAndUserId(String lectureId, String userId);
    List<LectureRegistrationEntity> findAllByUserId(String userId);
    List<LectureRegistrationEntity> findAllByLectureId(String lectureId);
}
