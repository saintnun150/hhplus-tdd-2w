package org.lowell.lectureregistration.domain.student.exception;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.common.ErrorCode;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@RequiredArgsConstructor
public enum StudentError implements ErrorCode {
    NOT_FOUND_STUDENT("STUDEN_ERR_01", "학생이 존재하지 않습니다."),
    DUPLICATED_STUDENT("STUDEN_ERR_02", "이미 존재하는 학생입니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(code, message);
    }
}
