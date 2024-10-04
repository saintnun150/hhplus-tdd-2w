package org.lowell.lectureregistration.domain.lectureAssignment.exception;

import lombok.Getter;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@Getter
public class LectureAssignmentException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public LectureAssignmentException(LectureAssignmentError error) {
        super(error.getErrorResponse().message());
        this.errorResponse = error.getErrorResponse();
    }
}
