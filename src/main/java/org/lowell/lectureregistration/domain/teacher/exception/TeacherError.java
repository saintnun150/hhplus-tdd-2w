package org.lowell.lectureregistration.domain.teacher.exception;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.common.ErrorCode;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@RequiredArgsConstructor
public enum TeacherError implements ErrorCode {
    NOT_FOUND_TEACHER("TEH_ERR_01", "강사가 존재하지 않습니다."),
    DUPLICATED_TEACHER("TEH_ERR_02", "이미 존재하는 강사입니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(code, message);
    }
}
