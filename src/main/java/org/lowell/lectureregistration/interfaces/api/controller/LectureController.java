package org.lowell.lectureregistration.interfaces.api.controller;

import lombok.RequiredArgsConstructor;
import org.lowell.lectureregistration.application.LectureRegistrationFacade;
import org.lowell.lectureregistration.domain.lecture.LectureCommand;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.interfaces.api.dto.LectureDto;
import org.lowell.lectureregistration.interfaces.api.mapper.LectureMapper;
import org.lowell.lectureregistration.interfaces.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lectures")
public class LectureController {
    private final LectureRegistrationFacade lectureRegistrationFacade;
    private final LectureMapper lectureMapper;

    // 1. 신청 가능한 강의 조회
    @GetMapping("/apply")
    public ApiResponse<List<LectureDto.LectureItem>> getApplicableLectures(LectureDto.SearchLectureRequest dto) {
        LectureCommand.SearchLectureRequest command = lectureMapper.toSearchCommand(dto);
        return ApiResponse.create(lectureRegistrationFacade.getLecturesByApplyDate(command));
    }

    // 2. 특강 신청 api
    @PostMapping("/apply")
    public ApiResponse<LectureDto.LectureItem> applyLecture(@RequestBody LectureDto.CreateLectureApplyRequest dto) {
        LectureCommand.CreateLectureApplyRequest command = lectureMapper.toCreateCommand(dto);
        return ApiResponse.create(lectureRegistrationFacade.applyLectureRegistration(command.lectureId(),
                                                                                     command.userId()));
    }

    // 3. 특강 신청 완료 목록 조회 API
    @GetMapping("/apply/histories")
    public ApiResponse<List<LectureRegistrationInfo>> getAppliedLectureHistories(LectureDto.SearchLectureRequest dto) {
        LectureCommand.SearchLectureRequest command = lectureMapper.toSearchCommand(dto);
        return ApiResponse.create(lectureRegistrationFacade.getAllLectureRegistrations(command));
    }
}
