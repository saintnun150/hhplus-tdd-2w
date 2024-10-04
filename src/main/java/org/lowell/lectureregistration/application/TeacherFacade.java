package org.lowell.lectureregistration.application;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.teacher.TeacherCommand;
import org.lowell.lectureregistration.domain.teacher.TeacherService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TeacherFacade {
    private final TeacherService teacherService;

    @Transactional
    public void createTeacher(TeacherCommand.Create command) {
        teacherService.create(command.teacherId());
    }
}
