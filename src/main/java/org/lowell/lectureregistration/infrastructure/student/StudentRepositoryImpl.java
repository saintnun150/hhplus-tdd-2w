package org.lowell.lectureregistration.infrastructure.student;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.student.StudentInfo;
import org.lowell.lectureregistration.domain.student.StudentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final StudentJpaRepository jpaRepository;

    @Override
    public StudentInfo getStudentInfo(String studentId) {
        StudentEntity studentEntity = jpaRepository.findById(studentId)
                                                   .orElse(null);
        return StudentEntity.toPojo(studentEntity);
    }

    @Override
    public StudentInfo insert(String studentId) {
        StudentEntity studentEntity = StudentEntity.builder()
                                                   .userId(studentId)
                                                   .username(studentId + "군")
                                                   .createdAt(LocalDateTime.now())
                                                   .build();
        StudentEntity save = jpaRepository.save(studentEntity);
        return StudentEntity.toPojo(save);
    }

    @Override
    public List<StudentInfo> getAllStudentInfo() {
        List<StudentEntity> items = jpaRepository.findAll();
        return StudentEntity.toPojoList(items);
    }


}
