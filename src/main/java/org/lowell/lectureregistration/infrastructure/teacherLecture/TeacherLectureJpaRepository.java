package org.lowell.lectureregistration.infrastructure.teacherLecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherLectureJpaRepository extends JpaRepository<TeacherLectureEntity, TeacherLectureEntity.ID> {

    @Query("select tl from TeacherLectureEntity tl where tl.teacherId =:teacherId and tl.lectureId =:lectureId")
    List<TeacherLectureEntity> getAllTeacherLectureInfo(String teacherId, String lectureId);
}
