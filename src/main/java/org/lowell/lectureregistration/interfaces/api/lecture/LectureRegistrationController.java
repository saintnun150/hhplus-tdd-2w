package org.lowell.lectureregistration.interfaces.api.lecture;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.application.LectureFacade;
import org.lowell.lectureregistration.application.LectureRegistrationFacade;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.interfaces.api.dto.LectureDto;
import org.lowell.lectureregistration.interfaces.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lecture-registrations")
public class LectureRegistrationController {

    private final LectureFacade lectureFacade;
    private final LectureRegistrationFacade lectureRegistrationFacade;

    // 1. 신청 가능한 강의 조회
    @GetMapping("/lectures")
    public ApiResponse<List<LectureDto.LectureItem>> getApplicableLectures(LectureDto.SearchAvailLecture dto) {
        return ApiResponse.create(lectureFacade.getAvailableLectureInfoList(dto));
    }

    // 2. 특강 신청 api
    @PostMapping("/lectures/apply")
    public ApiResponse<LectureInfo> applyLecture(@RequestBody LectureDto.RequestApply dto) {
        return ApiResponse.create(lectureRegistrationFacade.applyLectureRegistration(dto.lectureId(), dto.userId()));
    }

    // 3. 특강 신청 완료 목록 조회 API
    @GetMapping("/lectures/apply/histories")
    public ApiResponse<List<LectureRegistrationInfo>> getAppliedLectureHistories(LectureDto.RequestSearch dto) {
        return ApiResponse.create(lectureRegistrationFacade.getAllLectureRegistrations(dto.lectureId()));
    }
}
