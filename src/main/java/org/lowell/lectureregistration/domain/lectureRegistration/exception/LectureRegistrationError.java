package org.lowell.lectureregistration.domain.lectureRegistration.exception;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.common.ErrorCode;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@RequiredArgsConstructor
public enum LectureRegistrationError implements ErrorCode {
    NOT_FOUND_LECTURE_REGISTRATION("REG_ERR_01", "수강신청 정보가 존재하지 않습니다."),
    DUPLICATED_LECTURE_REGISTRATION("REG_ERR_02", "이미 수강 신청한 강의입니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(code, message);
    }
}
