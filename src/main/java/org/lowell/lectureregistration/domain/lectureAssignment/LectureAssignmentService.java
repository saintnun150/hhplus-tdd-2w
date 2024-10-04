package org.lowell.lectureregistration.domain.lectureAssignment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lowell.lectureregistration.domain.lectureAssignment.exception.LectureAssignmentError;
import org.lowell.lectureregistration.domain.lectureAssignment.exception.LectureAssignmentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureAssignmentService {
    private final LectureAssignmentRepository teacherLectureRepository;

    public void create(String lectureId, String teacherId) {
        LectureAssignmentInfo lectureAssignmentInfo = teacherLectureRepository.getLectureAssignmentInfo(lectureId, teacherId);
        if (lectureAssignmentInfo != null) {
            throw new LectureAssignmentException(LectureAssignmentError.DUPLICATED_ASSIGNMENT);
        }

        teacherLectureRepository.insert(lectureId, teacherId);
    }

    public List<LectureAssignmentInfo> getAllLectureAssignments() {
        return teacherLectureRepository.getAllLectureAssignments();
    }

    public List<LectureAssignmentInfo> getAllLectureAssignmentsByLecture(String lectureId) {
        return teacherLectureRepository.getAllLectureAssignmentsByLecture(lectureId);
    }

    public List<LectureAssignmentInfo> getAllLectureAssignmentsByTeacher(String teacherId) {
        return teacherLectureRepository.getAllLectureAssignmentsByTeacher(teacherId);
    }
}
