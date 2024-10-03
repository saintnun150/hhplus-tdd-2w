package org.lowell.lectureregistration.domain.student;

public interface StudentRepository {
    StudentInfo getStudentInfo(String studentId);
    StudentInfo insert(String studentId);
}
