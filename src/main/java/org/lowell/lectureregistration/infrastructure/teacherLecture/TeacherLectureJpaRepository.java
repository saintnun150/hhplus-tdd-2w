package org.lowell.lectureregistration.infrastructure.teacherLecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherLectureJpaRepository extends JpaRepository<TeacherLectureEntity, TeacherLectureEntity.ID> {
    List<TeacherLectureEntity> getAllTeacherLectureInfo(String teacherId, String lectureId);
}
