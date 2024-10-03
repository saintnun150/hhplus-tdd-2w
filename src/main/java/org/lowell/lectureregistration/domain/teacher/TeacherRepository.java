package org.lowell.lectureregistration.domain.teacher;

public interface TeacherRepository {
    TeacherInfo getTeacherInfo(String teacherId);
    TeacherInfo insert(String teacherId);
}
