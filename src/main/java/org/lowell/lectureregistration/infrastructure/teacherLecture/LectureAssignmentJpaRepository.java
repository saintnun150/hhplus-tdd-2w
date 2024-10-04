package org.lowell.lectureregistration.infrastructure.teacherLecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureAssignmentJpaRepository extends JpaRepository<LectureAssignmentEntity, Long> {
    List<LectureAssignmentEntity> findAllByLectureId(String lectureId);

    List<LectureAssignmentEntity> findAllByTeacherId(String teacherId);

    Optional<LectureAssignmentEntity> findByLectureIdAndTeacherId(String teacherId, String lectureId);

}
