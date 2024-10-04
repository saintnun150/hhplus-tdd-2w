package org.lowell.lectureregistration.application;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lecture.LectureCommand;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.domain.lecture.LectureService;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationService;
import org.lowell.lectureregistration.domain.student.StudentInfo;
import org.lowell.lectureregistration.domain.student.StudentService;
import org.lowell.lectureregistration.interfaces.api.dto.LectureDto;
import org.lowell.lectureregistration.interfaces.api.mapper.LectureMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureRegistrationFacade {
    private final LectureService lectureService;
    private final LectureRegistrationService lectureRegistrationService;
    private final StudentService studentService;

    private final LectureMapper mapper;

    @Transactional
    public LectureDto.LectureItem applyLectureRegistration(String lectureId, String userId) {
        StudentInfo studentInfo = studentService.getStudentInfo(userId);
        LectureInfo lectureInfo = lectureService.getLectureInfo(lectureId);
        lectureInfo.checkLectureRegistrationStatus(LocalDateTime.now());

        lectureRegistrationService.createLectureRegistration(lectureId,
                                                             studentInfo.userId());
        LectureInfo item = lectureService.increaseCurrentRegistrationCnt(lectureId);
        return mapper.toResponse(item);
    }

    public List<LectureRegistrationInfo> getAllLectureRegistrations(LectureCommand.SearchLectureRequest command) {
        if (command == null) {
            return null;
        }
        if (command.lectureId() != null) {
            return lectureRegistrationService.getAllLectureRegistrationByLecture(command.lectureId());
        }
        return lectureRegistrationService.getAllLectureRegistrations();
    }

    public List<LectureDto.LectureItem> getLecturesByApplyDate(LectureCommand.SearchLectureRequest command) {
        List<LectureInfo> items = lectureService.getLecturesByApplyDate(command.applyDate());
        List<LectureDto.LectureItem> result = new ArrayList<>();
        for (LectureInfo item : items) {
            result.add(mapper.toResponse(item));
        }
        return result;
    }

}
