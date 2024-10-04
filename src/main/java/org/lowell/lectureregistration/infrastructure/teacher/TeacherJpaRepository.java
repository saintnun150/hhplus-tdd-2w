package org.lowell.lectureregistration.infrastructure.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<TeacherEntity, String> {
}
