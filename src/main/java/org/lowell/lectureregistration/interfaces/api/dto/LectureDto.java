package org.lowell.lectureregistration.interfaces.api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureDto {

    @Data
    @NoArgsConstructor
    public static class SearchLectureRequest {
        private String lectureId;
        private LocalDateTime applyDate;
    }

    @Data
    @NoArgsConstructor
    public static class CreateLectureApplyRequest {
        private String lectureId;
        private String userId;
    }

    public record LectureItem(String lectureId, String lectureName, String lectureDescription,
                              LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
                              LocalDateTime appliedAt,
                              int currentRegistrationCnt) {

    }
}
