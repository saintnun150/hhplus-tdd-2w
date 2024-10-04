package org.lowell.lectureregistration.infrastructure.lectureRegistration;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepositoryImpl implements LectureRegistrationRepository {
    private final LectureRegistrationJpaRepository jpaRepository;

    @Override
    public LectureRegistrationInfo getLectureRegistrationInfo(String lectureId, String userId) {
        LectureRegistrationEntity entity = jpaRepository.findById(new LectureRegistrationEntity.ID(lectureId, userId))
                                                        .orElse(null);
        return LectureRegistrationEntity.toPojo(entity);
    }

    @Transactional
    @Override
    public LectureRegistrationInfo insert(String lectureId, String userId) {
        LectureRegistrationEntity entity = LectureRegistrationEntity.builder()
                                                                   .lectureId(lectureId)
                                                                   .userId(userId)
                                                                   .createdAt(LocalDateTime.now())
                                                                   .build();
        LectureRegistrationEntity save = jpaRepository.save(entity);
        return LectureRegistrationEntity.toPojo(save);
    }

    @Override
    public List<LectureRegistrationInfo> getAllLectureRegistrationInfoByUserId(String userId) {
        List<LectureRegistrationEntity> entities = jpaRepository.findAllByUserId(userId);
        return LectureRegistrationEntity.toPojoList(entities);
    }

    @Override
    public List<LectureRegistrationInfo> getAllLectureRegistrationInfoByLectureId(String lectureId) {
        List<LectureRegistrationEntity> entities = jpaRepository.findAllByLectureId(lectureId);
        return LectureRegistrationEntity.toPojoList(entities);
    }

}
