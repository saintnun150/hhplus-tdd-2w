package org.lowell.lectureregistration.domain.teacherLecture.exception;

import lombok.Getter;
import org.lowell.lectureregistration.domain.common.ErrorResponse;

@Getter
public class TeacherLectureException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public TeacherLectureException(TeacherLectureError error) {
        super(error.getErrorResponse().message());
        this.errorResponse = error.getErrorResponse();
    }
}
