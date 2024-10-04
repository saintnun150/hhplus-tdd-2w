package org.lowell.lectureregistration.application;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.student.StudentCommand;
import org.lowell.lectureregistration.domain.student.StudentInfo;
import org.lowell.lectureregistration.domain.student.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudentFacade {
    private final StudentService studentService;

    @Transactional
    public StudentInfo createStudent(StudentCommand.Create command) {
        return studentService.create(command.studentId());
    }
}
