package org.lowell.lectureregistration.infrastructure.lectureAssignment;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lectureAssignment.LectureAssignmentInfo;
import org.lowell.lectureregistration.domain.lectureAssignment.LectureAssignmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureAssignmentRepositoryImpl implements LectureAssignmentRepository {
    private final LectureAssignmentJpaRepository jpaRepository;

    @Transactional
    @Override
    public LectureAssignmentInfo insert(String teacherId, String lectureId) {
        LectureAssignmentEntity entity = LectureAssignmentEntity.builder()
                                                                .teacherId(teacherId)
                                                                .lectureId(lectureId)
                                                                .createdAt(LocalDateTime.now())
                                                                .build();
        LectureAssignmentEntity save = jpaRepository.save(entity);
        return LectureAssignmentEntity.toPojo(save);
    }

    @Override
    public LectureAssignmentInfo getLectureAssignmentInfo(String lectureId, String teacherId) {
        LectureAssignmentEntity item = jpaRepository.findByLectureIdAndTeacherId(teacherId, lectureId)
                                                    .orElse(null);
        return LectureAssignmentEntity.toPojo(item);
    }

    @Override
    public List<LectureAssignmentInfo> getAllLectureAssignments() {
        List<LectureAssignmentEntity> items = jpaRepository.findAll();
        return LectureAssignmentEntity.toPojoList(items);
    }

    @Override
    public List<LectureAssignmentInfo> getAllLectureAssignmentsByLecture(String lectureId) {
        List<LectureAssignmentEntity> items = jpaRepository.findAllByLectureId(lectureId);
        return LectureAssignmentEntity.toPojoList(items);
    }

    @Override
    public List<LectureAssignmentInfo> getAllLectureAssignmentsByTeacher(String teacherId) {
        List<LectureAssignmentEntity> items = jpaRepository.findAllByTeacherId(teacherId);
        return LectureAssignmentEntity.toPojoList(items);
    }
}
