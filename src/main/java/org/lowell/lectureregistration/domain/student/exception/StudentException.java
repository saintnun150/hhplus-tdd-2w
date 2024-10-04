package org.lowell.lectureregistration.domain.student.exception;

import lombok.Getter;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@Getter
public class StudentException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public StudentException(StudentError error) {
        super(error.getErrorResponse().message());
        this.errorResponse = error.getErrorResponse();
    }
}
