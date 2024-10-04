package org.lowell.lectureregistration.infrastructure.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_lecture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LectureEntity {

    @Id
    @Column(name = "lecture_id")
    private String lectureId;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "lecture_description")
    private String lectureDescription;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "applied_at")
    private LocalDateTime appliedAt;

    @Column(name = "current_registration_cnt")
    private int currentRegistrationCnt;

    public void increaseCurrentRegistrationCnt() {
        this.currentRegistrationCnt++;
    }

    public static LectureInfo toPojo(LectureEntity lectureEntity) {
        if (lectureEntity == null) {
            return null;
        }
        return new LectureInfo(lectureEntity.getLectureId(),
                               lectureEntity.getLectureName(),
                               lectureEntity.getLectureDescription(),
                               lectureEntity.getCreatedAt(),
                               lectureEntity.getUpdatedAt(),
                               lectureEntity.getDeletedAt(),
                               lectureEntity.getAppliedAt(),
                               lectureEntity.getCurrentRegistrationCnt());
    }

    public static List<LectureInfo> toPojoList(List<LectureEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<LectureInfo> items = new ArrayList<>();
        for (LectureEntity entity : entities) {
            items.add(toPojo(entity));
        }
        return items;
    }

}
