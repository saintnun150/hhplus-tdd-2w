package org.lowell.lectureregistration.infrastructure.lectureRegistration;

import jakarta.persistence.*;
import lombok.*;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_lecture_registration")
@IdClass(LectureRegistrationEntity.ID.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureRegistrationEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7295778408568464652L;

    @Id
    private String lectureId;

    @Id
    private String userId;

    @Column
    private LocalDateTime createdAt;

    @Data
    @NoArgsConstructor
    public static class ID implements Serializable {
        @Serial
        private static final long serialVersionUID = 3387460001894584362L;

        private String lectureId;
        private String userId;

        public ID(String lectureId, String userId) {
            this.lectureId = lectureId;
            this.userId = userId;
        }
    }

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
