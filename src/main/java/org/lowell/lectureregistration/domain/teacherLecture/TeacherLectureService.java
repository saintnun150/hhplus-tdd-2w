package org.lowell.lectureregistration.domain.teacherLecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lowell.lectureregistration.domain.teacherLecture.exception.TeacherLectureError;
import org.lowell.lectureregistration.domain.teacherLecture.exception.TeacherLectureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherLectureService {
    private final TeacherLectureRepository teacherLectureRepository;

    public void create(String lectureId, String teacherId) {
        TeacherLectureInfo teacherLectureInfo = teacherLectureRepository.getTeacherLectureInfo(lectureId, teacherId);
        if (teacherLectureInfo != null) {
            throw new TeacherLectureException(TeacherLectureError.DUPLICATED_TEACHER_LECTURE);
        }

        teacherLectureRepository.insert(lectureId, teacherId);
    }

    public List<TeacherLectureInfo> getAllTeacherLectureInfo(String teacherId, String lectureId) {
        return teacherLectureRepository.getAllTeacherLecture(lectureId, teacherId);
    }
}
