package org.lowell.lectureregistration.domain.lecture.exception;

import lombok.Getter;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@Getter
public class LectureException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public LectureException(LectureError error) {
        super(error.getErrorResponse().message());
        this.errorResponse = error.getErrorResponse();
    }
}
