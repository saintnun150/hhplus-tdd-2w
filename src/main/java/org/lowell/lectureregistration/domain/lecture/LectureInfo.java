package org.lowell.lectureregistration.domain.lecture;

import org.lowell.lectureregistration.domain.lecture.exception.LectureError;
import org.lowell.lectureregistration.domain.lecture.exception.LectureException;
import org.lowell.lectureregistration.infrastructure.lecture.LectureEntity;
import org.lowell.lectureregistration.interfaces.api.dto.LectureDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record LectureInfo(String lectureId, String lectureName, String lectureDescription,
                          LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
                          LocalDateTime appliedAt,
                          int currentRegistrationCnt) {

    public static final int MAX_REGISTRATION_CNT = 30;

    public void checkLectureRegistrationStatus(LocalDateTime now) {
        if (appliedAt.isAfter(now) || now.isBefore(appliedAt)) {
            throw new LectureException(LectureError.INVALID_APPLY_DATE);
        }

        if (currentRegistrationCnt == MAX_REGISTRATION_CNT) {
            throw new LectureException(LectureError.LIMIT_REGISTRATION);
        }
    }

    public static LectureDto.LectureItem toPojo(LectureInfo lectureInfo) {
        if (lectureInfo == null) {
            return null;
        }
        return new LectureDto.LectureItem(lectureInfo.lectureId(),
                               lectureInfo.lectureName(),
                               lectureInfo.lectureDescription(),
                               lectureInfo.createdAt(),
                               lectureInfo.updatedAt(),
                               lectureInfo.deletedAt(),
                               lectureInfo.appliedAt(),
                               lectureInfo.currentRegistrationCnt());
    }

    public static List<LectureDto.LectureItem> toPojoList(List<LectureInfo> infos) {
        if (infos == null) {
            return null;
        }
        List<LectureDto.LectureItem> items = new ArrayList<>();
        for (LectureInfo info : infos) {
            items.add(toPojo(info));
        }
        return items;
    }

}
