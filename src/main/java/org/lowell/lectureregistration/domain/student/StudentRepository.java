package org.lowell.lectureregistration.domain.student;

import java.util.List;

public interface StudentRepository {
    StudentInfo getStudentInfo(String studentId);
    StudentInfo insert(String studentId);
    List<StudentInfo> getAllStudentInfo();
}
