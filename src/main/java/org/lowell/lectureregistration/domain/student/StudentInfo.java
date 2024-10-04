package org.lowell.lectureregistration.domain.student;

import java.time.LocalDateTime;

public record StudentInfo (String userId, String username, LocalDateTime createdAt) {
}
