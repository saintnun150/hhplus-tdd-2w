package org.lowell.lectureregistration.domain.lecture.exception;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.common.ErrorCode;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@RequiredArgsConstructor
public enum LectureError implements ErrorCode {
    NOT_FOUND_LECTURE("LECTURE_ERR_01", "강의가 존재하지 않습니다."),
    LIMIT_REGISTRATION("LECTURE_ERR_02", "신청 인원이 초과되었습니다."),
    INVALID_APPLY_DATE("LECTURE_ERR_03", "신청 가능한 날짜가 아닙니다."),
    NOT_FOUND_AVAILABLE_LECTURES("LECTURE_ERR_04", "신청 가능한 강의가 존재하지 않습니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(code, message);
    }
}
