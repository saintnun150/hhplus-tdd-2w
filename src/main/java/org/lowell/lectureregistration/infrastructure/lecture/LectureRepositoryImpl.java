package org.lowell.lectureregistration.infrastructure.lecture;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.domain.lecture.LectureRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository jpaRepository;

    @Override
    public LectureInfo getLectureInfo(String lectureId) {
        LectureEntity entity = jpaRepository.findById(lectureId)
                                            .orElse(null);
        return LectureEntity.toPojo(entity);
    }

    @Transactional
    @Override
    public LectureInfo increaseCurrentRegistrationCnt(String lectureId) {
        LectureEntity entity = jpaRepository.findById(lectureId)
                                            .orElseThrow(EntityNotFoundException::new);
        entity.increaseCurrentRegistrationCnt();
        return LectureEntity.toPojo(entity);
    }

    @Override
    public List<LectureInfo> getAvailableLectureInfoList(LocalDateTime applyDate) {
        List<LectureEntity> items = jpaRepository.getLecturesWithApplyDataAndNotDeleted(applyDate);
        return LectureEntity.toPojoList(items);
    }
}
