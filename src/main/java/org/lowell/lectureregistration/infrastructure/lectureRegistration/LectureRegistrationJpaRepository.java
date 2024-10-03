package org.lowell.lectureregistration.infrastructure.lectureRegistration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistrationEntity, LectureRegistrationEntity.ID> {
    List<LectureRegistrationEntity> findAllByUserId(String userId);
}
