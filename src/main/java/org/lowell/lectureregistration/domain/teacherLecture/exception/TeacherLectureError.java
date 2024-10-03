package org.lowell.lectureregistration.domain.teacherLecture.exception;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.common.ErrorCode;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@RequiredArgsConstructor
public enum TeacherLectureError implements ErrorCode {
    NOT_FOUND_TEACHER_LECTURE("TL_ERR_01", "해당 강의에 등록된 강사 정보가 존재하지 않습니다,"),
    DUPLICATED_TEACHER_LECTURE("TL_ERR_02", "이미 해당 강의에 등록되어있습니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(code, message);
    }
}
