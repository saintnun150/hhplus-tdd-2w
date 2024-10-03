package org.lowell.lectureregistration.infrastructure.teacher;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.teacher.TeacherInfo;
import org.lowell.lectureregistration.domain.teacher.TeacherRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {
    private final TeacherJpaRepository jpaRepository;


    @Override
    public TeacherInfo getTeacherInfo(String teacherId) {
        TeacherEntity teacherEntity = jpaRepository.findById(teacherId)
                                                   .orElse(null);
        return TeacherEntity.toPojo(teacherEntity);
    }

    @Override
    public TeacherInfo insert(String teacherId) {
        TeacherEntity entity = TeacherEntity.builder()
                                           .teacherId(teacherId)
                                           .username(teacherId + "강사")
                                           .createdAt(LocalDateTime.now())
                                           .build();
        TeacherEntity save = jpaRepository.save(entity);
        return TeacherEntity.toPojo(save);
    }
}
