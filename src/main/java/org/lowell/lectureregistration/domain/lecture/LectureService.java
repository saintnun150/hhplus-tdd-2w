package org.lowell.lectureregistration.domain.lecture;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.domain.lecture.exception.LectureError;
import org.lowell.lectureregistration.domain.lecture.exception.LectureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureInfo getLectureInfo(String lectureId) {
        LectureInfo lectureInfo = lectureRepository.getLectureInfoWithLock(lectureId);
        if (lectureInfo == null) {
            throw new LectureException(LectureError.NOT_FOUND_LECTURE);
        }
        return lectureInfo;
    }

    public List<LectureInfo> getAvailableLectureInfoList(LocalDateTime applyDate) {
        List<LectureInfo> lectureInfoList = lectureRepository.getAvailableLectureInfoList(applyDate);
        if (lectureInfoList == null) {
            throw new LectureException(LectureError.NOT_FOUND_AVAILABLE_LECTURES);
        }
        return lectureInfoList;
    }


    public LectureInfo increaseCurrentRegistrationCnt(String lectureId) {
        return lectureRepository.increaseCurrentRegistrationCnt(lectureId);
    }

    public void insertLecture(LectureCommand.Create lectureCommand) {
        lectureRepository.insert(lectureCommand.lectureId(),
                                 lectureCommand.appliedAt());
    }
}
