package org.lowell.lectureregistration.infrastructure.teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lowell.lectureregistration.domain.teacher.TeacherInfo;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_teacher")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TeacherEntity {

    @Id
    private String teacherId;

    @Column
    private String username;

    @Column
    private LocalDateTime createdAt;

    public static TeacherInfo toPojo(TeacherEntity teacherEntity) {
        if (teacherEntity == null) {
            return null;
        }
        return new TeacherInfo(teacherEntity.getTeacherId(),
                               teacherEntity.getUsername(),
                               teacherEntity.getCreatedAt());
    }
}
