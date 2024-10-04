package org.lowell.lectureregistration.interfaces.api.mapper;

import org.lowell.lectureregistration.domain.lecture.LectureCommand;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.interfaces.api.dto.LectureDto;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {

    public LectureCommand.SearchLectureRequest toSearchCommand(LectureDto.SearchLectureRequest dto) {
        return new LectureCommand.SearchLectureRequest(dto.getLectureId(), dto.getApplyDate());
    }

    public LectureDto.LectureItem toResponse(LectureInfo lectureInfo) {
        return new LectureDto.LectureItem(lectureInfo.lectureId(),
                                          lectureInfo.lectureName(),
                                          lectureInfo.lectureDescription(),
                                          lectureInfo.createdAt(),
                                          lectureInfo.updatedAt(),
                                          lectureInfo.deletedAt(),
                                          lectureInfo.appliedAt(),
                                          lectureInfo.currentRegistrationCnt());
    }

    public LectureCommand.CreateLectureApplyRequest toCreateCommand(LectureDto.CreateLectureApplyRequest dto) {
        return new LectureCommand.CreateLectureApplyRequest(dto.getLectureId(), dto.getUserId());
    }



}
