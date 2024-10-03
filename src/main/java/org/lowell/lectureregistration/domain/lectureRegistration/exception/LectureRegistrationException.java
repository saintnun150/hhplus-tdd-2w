package org.lowell.lectureregistration.domain.lectureRegistration.exception;

import lombok.Getter;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@Getter
public class LectureRegistrationException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public LectureRegistrationException(LectureRegistrationError error) {
        super(error.getErrorResponse().message());
        this.errorResponse = error.getErrorResponse();
    }
}
