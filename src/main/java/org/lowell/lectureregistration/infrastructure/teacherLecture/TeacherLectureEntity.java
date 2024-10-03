package org.lowell.lectureregistration.infrastructure.teacherLecture;

import jakarta.persistence.*;
import lombok.*;
import org.lowell.lectureregistration.domain.teacherLecture.TeacherLectureInfo;
import org.lowell.lectureregistration.infrastructure.lectureRegistration.LectureRegistrationEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_teacher_lecture_n")
@IdClass(LectureRegistrationEntity.ID.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherLectureEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4972259441338937251L;

    @Id
    private String teacherId;

    @Id
    private String lectureId;

    @Column
    private LocalDateTime createdAt;


    @Data
    @NoArgsConstructor
    public static class ID implements Serializable {

        @Serial
        private static final long serialVersionUID = 350300882796610814L;

        private String teacherId;
        private String lectureId;

        public ID(String teacherId, String lectureId) {
            this.teacherId = teacherId;
            this.lectureId = lectureId;
        }
    }

    public static TeacherLectureInfo toPojo(TeacherLectureEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TeacherLectureInfo(entity.getTeacherId(),
                                      entity.getLectureId(),
                                      entity.getCreatedAt());
    }

    public static List<TeacherLectureInfo> toPojoList(List<TeacherLectureEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<TeacherLectureInfo> items = new ArrayList<>();
        for (TeacherLectureEntity entity : entities) {
            items.add(toPojo(entity));
        }
        return items;
    }

}
