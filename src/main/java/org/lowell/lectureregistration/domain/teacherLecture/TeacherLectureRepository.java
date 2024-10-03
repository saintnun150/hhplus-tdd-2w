package org.lowell.lectureregistration.domain.teacherLecture;


import java.util.List;

public interface TeacherLectureRepository {
    TeacherLectureInfo getTeacherLectureInfo(String teacherId, String lectureId);
    TeacherLectureInfo insert(String teacherId, String lectureId);

    List<TeacherLectureInfo> getAllTeacherLecture(String teacherId, String lectureId);
}
