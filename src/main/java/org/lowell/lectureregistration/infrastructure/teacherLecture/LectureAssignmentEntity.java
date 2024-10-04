package org.lowell.lectureregistration.infrastructure.teacherLecture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lowell.lectureregistration.domain.lectureAssignment.LectureAssignmentInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_lecture_assignment",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"lecture_id", "teacher_id"})
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureAssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @Column(nullable = false)
    private String lectureId;

    @Column(nullable = false)
    private String teacherId;

    @Column
    private LocalDateTime createdAt;


    public static LectureAssignmentInfo toPojo(LectureAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LectureAssignmentInfo(entity.getTeacherId(),
                                         entity.getLectureId(),
                                         entity.getCreatedAt());
    }

    public static List<LectureAssignmentInfo> toPojoList(List<LectureAssignmentEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<LectureAssignmentInfo> items = new ArrayList<>();
        for (LectureAssignmentEntity entity : entities) {
            items.add(toPojo(entity));
        }
        return items;
    }

}
