package org.lowell.lectureregistration.domain.lecture;

import java.time.LocalDateTime;

public class LectureCommand {

    public record CreateLecture(String lectureId, LocalDateTime appliedAt, String teacherId) { }

    public record SearchLectureRequest(String lectureId, LocalDateTime applyDate) { }

    public record CreateLectureApplyRequest(String lectureId, String userId) {
    }
}
