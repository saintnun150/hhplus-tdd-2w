package org.lowell.lectureregistration.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {
    LectureInfo getLectureInfo(String lectureId);
    LectureInfo increaseCurrentRegistrationCnt(String lectureId);
    List<LectureInfo> getAvailableLectureInfoList(LocalDateTime applyDate);
}
