package org.lowell.lectureregistration.domain.lectureRegistration;

import java.time.LocalDateTime;

public record LectureRegistrationInfo(String lectureId, String userId, LocalDateTime createdAt) {
}
