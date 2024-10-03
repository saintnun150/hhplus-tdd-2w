package org.lowell.lectureregistration.infrastructure.teacherLecture;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.teacherLecture.TeacherLectureInfo;
import org.lowell.lectureregistration.domain.teacherLecture.TeacherLectureRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherLectureRepositoryImpl implements TeacherLectureRepository {
    private final TeacherLectureJpaRepository jpaRepository;

    @Override
    public TeacherLectureInfo getTeacherLectureInfo(String teacherId, String lectureId) {
        TeacherLectureEntity entity = jpaRepository.findById(new TeacherLectureEntity.ID(teacherId, lectureId))
                                                   .orElse(null);
        return TeacherLectureEntity.toPojo(entity);
    }

    @Transactional
    @Override
    public TeacherLectureInfo insert(String teacherId, String lectureId) {
        TeacherLectureEntity entity = TeacherLectureEntity.builder()
                                                          .teacherId(teacherId)
                                                          .lectureId(lectureId)
                                                          .createdAt(LocalDateTime.now())
                                                          .build();
        TeacherLectureEntity save = jpaRepository.save(entity);
        return TeacherLectureEntity.toPojo(save);
    }

    @Override
    public List<TeacherLectureInfo> getAllTeacherLecture(String teacherId, String lectureId) {
        List<TeacherLectureEntity> items = jpaRepository.getAllTeacherLectureInfo(teacherId, lectureId);
        return TeacherLectureEntity.toPojoList(items);
    }
}
