package org.lowell.lectureregistration.domain.lectureAssignment;

import java.time.LocalDateTime;

public record LectureAssignmentInfo(String teacherId, String lectureId, LocalDateTime createdAt) {
}
