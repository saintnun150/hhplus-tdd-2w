package org.lowell.lectureregistration.domain.teacherLecture;

import java.time.LocalDateTime;

public record TeacherLectureInfo(String teacherId, String lectureId, LocalDateTime createdAt) {
}
