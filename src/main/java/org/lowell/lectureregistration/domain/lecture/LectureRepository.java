package org.lowell.lectureregistration.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {
    LectureInfo getLectureInfo(String lectureId);
    LectureInfo getLectureInfoWithLock(String lectureId);
    LectureInfo increaseCurrentRegistrationCnt(String lectureId);
    LectureInfo insert(String lectureId, LocalDateTime applyDate);
    List<LectureInfo> getLecturesByApplyDate(LocalDateTime applyDate);
}
