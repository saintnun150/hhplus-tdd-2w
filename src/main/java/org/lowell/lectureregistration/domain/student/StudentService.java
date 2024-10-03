package org.lowell.lectureregistration.domain.student;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.student.exception.StudentError;
import org.lowell.lectureregistration.domain.student.exception.StudentException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentInfo getStudentInfo(String studentId) {
        StudentInfo studentInfo = studentRepository.getStudentInfo(studentId);
        if (studentInfo == null) {
            throw new StudentException(StudentError.NOT_FOUND_STUDENT);
        }
        return studentInfo;
    }

    public StudentInfo create(String studentId) {
        StudentInfo studentInfo = studentRepository.getStudentInfo(studentId);
        if (studentInfo != null) {
            throw new StudentException(StudentError.DUPLICATED_STUDENT);
        }
        return studentRepository.insert(studentId);
    }
}
