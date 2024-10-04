package org.lowell.lectureregistration.interfaces.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureDto {

    public record LectureItem(String lectureId, String lectureName, String lectureDescription,
                              LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
                              LocalDateTime appliedAt,
                              int currentRegistrationCnt) {

    }

    public record SearchAvailLecture(LocalDateTime applyDate) {
    }

    public record RequestApply(String lectureId, String userId) {

    }

    public record RequestSearch(String lectureId) {

    }
}
