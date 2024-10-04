package org.lowell.lectureregistration.domain.lectureRegistration;

import java.util.List;

public interface LectureRegistrationRepository {
    LectureRegistrationInfo getLectureRegistrationInfo(String lectureId, String userId);
    LectureRegistrationInfo insert(String lectureId, String userId);
    List<LectureRegistrationInfo> getAllLectureRegistrationInfoByUserId(String userId);
    List<LectureRegistrationInfo> getAllLectureRegistrationInfoByLectureId(String lectureId);

}
