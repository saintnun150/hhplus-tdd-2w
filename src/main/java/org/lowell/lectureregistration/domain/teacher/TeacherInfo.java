package org.lowell.lectureregistration.domain.teacher;

import java.time.LocalDateTime;

public record TeacherInfo(String teacherId, String username, LocalDateTime createdAt) {
}
