package org.lowell.lectureregistration.domain.lectureAssignment;


import java.util.List;

public interface LectureAssignmentRepository {
    LectureAssignmentInfo insert(String lectureId, String teacherId);
    LectureAssignmentInfo getLectureAssignmentInfo(String lectureId, String teacherId);
    List<LectureAssignmentInfo> getAllLectureAssignments();
    List<LectureAssignmentInfo> getAllLectureAssignmentsByLecture(String lectureId);
    List<LectureAssignmentInfo> getAllLectureAssignmentsByTeacher(String teacherId);
}
