package org.lowell.lectureregistration.domain.lectureRegistration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lowell.lectureregistration.domain.lectureRegistration.exception.LectureRegistrationError;
import org.lowell.lectureregistration.domain.lectureRegistration.exception.LectureRegistrationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureRegistrationService {
    private final LectureRegistrationRepository lectureRegistrationRepository;

    public void createLectureRegistration(String lectureId, String userId) {
        LectureRegistrationInfo registrationInfo = lectureRegistrationRepository.getLectureRegistrationInfo(lectureId,
                                                                                                            userId);
        if (registrationInfo != null) {
            throw new LectureRegistrationException(LectureRegistrationError.DUPLICATED_LECTURE_REGISTRATION);
        }

        lectureRegistrationRepository.insert(lectureId, userId);
    }

    public List<LectureRegistrationInfo> getAllLectureRegistrationByUser(String userId) {
        return lectureRegistrationRepository.getAllLectureRegistrationInfoByUser(userId);
    }

    public List<LectureRegistrationInfo> getAllLectureRegistrationByLecture(String lectureId) {
        return lectureRegistrationRepository.getAllLectureRegistrationInfoByLecture(lectureId);
    }
}
