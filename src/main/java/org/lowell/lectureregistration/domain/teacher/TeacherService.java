package org.lowell.lectureregistration.domain.teacher;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.teacher.exception.TeacherError;
import org.lowell.lectureregistration.domain.teacher.exception.TeacherException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherInfo getTeacherInfo(String teacherId) {
        return teacherRepository.getTeacherInfo(teacherId);
    }

    public TeacherInfo createTeacher(String teacherId) {
        TeacherInfo teacherInfo = teacherRepository.getTeacherInfo(teacherId);
        if (teacherInfo != null) {
            throw new TeacherException(TeacherError.DUPLICATED_TEACHER);
        }
        return teacherRepository.insert(teacherId);
    }
}
