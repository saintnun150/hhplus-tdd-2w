package org.lowell.lectureregistration.domain.lecture;

import org.lowell.lectureregistration.domain.lecture.exception.LectureError;
import org.lowell.lectureregistration.domain.lecture.exception.LectureException;

import java.time.LocalDateTime;

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

}
