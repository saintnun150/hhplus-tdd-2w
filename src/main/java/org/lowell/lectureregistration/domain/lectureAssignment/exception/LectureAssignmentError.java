package org.lowell.lectureregistration.domain.lectureAssignment.exception;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.common.ErrorCode;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@RequiredArgsConstructor
public enum LectureAssignmentError implements ErrorCode {
    NOT_FOUND_ASSIGNMENT("AS_ERR_01", "할당된 정보가 없습니다."),
    DUPLICATED_ASSIGNMENT("AS_ERR_02", "이미 할당된 강의입니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(code, message);
    }
}
