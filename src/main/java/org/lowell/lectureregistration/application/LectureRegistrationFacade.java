package org.lowell.lectureregistration.application;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.domain.lecture.LectureService;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationCommand;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationService;
import org.lowell.lectureregistration.domain.student.StudentInfo;
import org.lowell.lectureregistration.domain.student.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureRegistrationFacade {
    private final LectureService lectureService;
    private final LectureRegistrationService lectureRegistrationService;
    private final StudentService studentService;

    @Transactional
    public LectureInfo applyLectureRegistration(String lectureId, String userId) {
        StudentInfo studentInfo = studentService.getStudentInfo(userId);
        LectureInfo lectureInfo = lectureService.getLectureInfo(lectureId);
        lectureInfo.checkLectureRegistrationStatus(LocalDateTime.now());

        lectureRegistrationService.registerLecture(lectureId,
                                                   studentInfo.userId());
        return lectureService.increaseCurrentRegistrationCnt(lectureId);
    }

    @Transactional
    public List<LectureRegistrationInfo> getAllLectureRegistrations(String lectureId) {
        return lectureRegistrationService.getAllLectureRegistrationInfoByLectureId(lectureId);
    }
}
