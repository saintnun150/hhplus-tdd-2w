package org.lowell.lectureregistration.infrastructure.lectureRegistration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_lecture_registration")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureRegistrationId;

    @Column(nullable = false)
    private String lectureId;

    @Column(nullable = false)
    private String userId;

    @Column
    private LocalDateTime createdAt;

    public static LectureRegistrationInfo toPojo(LectureRegistrationEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LectureRegistrationInfo(entity.getLectureId(),
                                           entity.getUserId(),
                                           entity.getCreatedAt());
    }

    public static List<LectureRegistrationInfo> toPojoList(List<LectureRegistrationEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<LectureRegistrationInfo> items = new ArrayList<>();
        for (LectureRegistrationEntity entity : entities) {
            items.add(toPojo(entity));
        }
        return items;
    }

}
