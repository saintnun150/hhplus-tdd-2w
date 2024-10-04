package org.lowell.lectureregistration.domain.teacher.exception;

import lombok.Getter;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@Getter
public class TeacherException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public TeacherException(TeacherError error) {
        super(error.getErrorResponse().message());
        this.errorResponse = error.getErrorResponse();
    }
}
