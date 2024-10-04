package org.lowell.lectureregistration.application;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lecture.LectureCommand;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.domain.lecture.LectureService;
import org.lowell.lectureregistration.domain.teacherLecture.TeacherLectureService;
import org.lowell.lectureregistration.interfaces.api.dto.LectureDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureFacade {
    private final LectureService lectureService;
    private final TeacherLectureService teacherLectureService;

    @Transactional
    public void createLecture(LectureCommand.Create command) {
        lectureService.insertLecture(command);
        teacherLectureService.create(command.lectureId(),
                                     command.teacherId());
    }

    public List<LectureDto.LectureItem> getAvailableLectureInfoList(LectureDto.SearchAvailLecture dto) {
        List<LectureInfo> items = lectureService.getAvailableLectureInfoList(dto.applyDate());
        return LectureInfo.toPojoList(items);
    }
}
