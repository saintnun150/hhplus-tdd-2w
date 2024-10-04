package org.lowell.lectureregistration.infrastructure.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lowell.lectureregistration.domain.student.StudentInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class StudentEntity {

    @Id
    private String userId;

    @Column
    private String username;

    @Column
    private LocalDateTime createdAt;

    public static StudentInfo toPojo(StudentEntity studentEntity) {
        if (studentEntity == null) {
            return null;
        }
        return new StudentInfo(studentEntity.getUserId(),
                               studentEntity.getUsername(),
                               studentEntity.getCreatedAt());
    }

    public static List<StudentInfo> toPojoList(List<StudentEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<StudentInfo> items = new ArrayList<>();
        for (StudentEntity entity : entities) {
            items.add(toPojo(entity));
        }
        return items;
    }
}
