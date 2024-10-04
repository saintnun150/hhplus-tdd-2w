package org.lowell.lectureregistration.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lowell.lectureregistration.domain.lecture.LectureInfo;
import org.lowell.lectureregistration.domain.lecture.LectureRepository;
import org.lowell.lectureregistration.domain.lecture.LectureService;
import org.lowell.lectureregistration.domain.lecture.exception.LectureError;
import org.lowell.lectureregistration.domain.lecture.exception.LectureException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private LectureService lectureService;


    @DisplayName("없는 강의 id를 조회 시 예외가 발생한다.")
    @Test
    void throwException_when_input_invalid_lectureId() {
        String lectureId = "lectureId44";

        when(lectureRepository.getLectureInfo(lectureId))
                .thenReturn(null);

        assertThatThrownBy(() -> lectureService.getLectureInfo(lectureId))
                .isInstanceOf(LectureException.class)
                .extracting(e -> ((LectureException) e).getErrorResponse().code(),
                            e -> ((LectureException) e).getErrorResponse().message())
                .containsExactly(LectureError.NOT_FOUND_LECTURE.getErrorResponse().code(),
                                 LectureError.NOT_FOUND_LECTURE.getErrorResponse().message());
    }

    @DisplayName("신청인원이 초과되면 예외를 발생한다.")
    @Test
    void throwException_when_limit_registration_cnt_exceeded() {
        String lectureId = "lectureId1";
        when(lectureRepository.getLectureInfo(lectureId))
                .thenReturn(new LectureInfo(lectureId, "강의1", "테스트강의",
                                            LocalDateTime.now(),
                                            null,
                                            null,
                                            LocalDateTime.now().with(LocalTime.MIN), 30));

        assertThatThrownBy(() -> lectureService.getLectureInfo(lectureId)
                                               .checkLectureRegistrationStatus(LocalDateTime.now().with(LocalTime.MIN)))
                .isInstanceOf(LectureException.class)
                .extracting(e -> ((LectureException) e).getErrorResponse().code(),
                            e -> ((LectureException) e).getErrorResponse().message())
                .containsExactly(LectureError.LIMIT_REGISTRATION.getErrorResponse().code(),
                                 LectureError.LIMIT_REGISTRATION.getErrorResponse().message());

    }

    @DisplayName("신청날짜가 잘못되면 예외를 발생한다.")
    @Test
    void throwException_when_apply_invalid_date() {
        String lectureId = "lectureId1";
        when(lectureRepository.getLectureInfo(lectureId))
                .thenReturn(new LectureInfo(lectureId, "강의1", "테스트강의",
                                            LocalDateTime.now(),
                                            null,
                                            null,
                                            LocalDateTime.now().plusDays(1),
                                            10));

        assertThatThrownBy(() -> lectureService.getLectureInfo(lectureId)
                                               .checkLectureRegistrationStatus(LocalDateTime.now().with(LocalTime.MIN)))
                .isInstanceOf(LectureException.class)
                .extracting(e -> ((LectureException) e).getErrorResponse().code(),
                            e -> ((LectureException) e).getErrorResponse().message())
                .containsExactly(LectureError.INVALID_APPLY_DATE.getErrorResponse().code(),
                                 LectureError.INVALID_APPLY_DATE.getErrorResponse().message());

    }

    @DisplayName("신청일 기준 신청 가능한 강의가 없을 경우 예외를 발생한다.")
    @Test
    void throwException_when_available_lecture_is_not_found() {
        LocalDateTime applyDate = LocalDateTime.now().with(LocalTime.MIN);

        when(lectureRepository.getLecturesByApplyDate(applyDate)).thenReturn(null);

        assertThatThrownBy(() -> lectureService.getLecturesByApplyDate(applyDate))
                .extracting(e -> ((LectureException) e).getErrorResponse().code(),
                            e -> ((LectureException) e).getErrorResponse().message())
                .containsExactly(LectureError.NOT_FOUND_AVAILABLE_LECTURES.getErrorResponse().code(),
                                 LectureError.NOT_FOUND_AVAILABLE_LECTURES.getErrorResponse().message());
    }

    @DisplayName("신청이 완료될 경우 현재 신청인원수를 증가시킨다")
    @Test
    void increaseCurrentRegistrationCntWhenApplyCompleted() {
        String lectureId = "lectureId1";
        when(lectureRepository.increaseCurrentRegistrationCnt(lectureId)).thenReturn(new LectureInfo(lectureId, "강의1", "테스트강의",
                                                                                                     LocalDateTime.now(),
                                                                                                     null,
                                                                                                     null,
                                                                                                     LocalDateTime.now().plusDays(1),
                                                                                                     11));
        LectureInfo lectureInfo = lectureService.increaseCurrentRegistrationCnt(lectureId);
        assertThat(lectureInfo.currentRegistrationCnt()).isEqualTo(11);
    }
}