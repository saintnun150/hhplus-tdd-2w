package org.lowell.lectureregistration.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationRepository;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationService;
import org.lowell.lectureregistration.domain.lectureRegistration.exception.LectureRegistrationError;
import org.lowell.lectureregistration.domain.lectureRegistration.exception.LectureRegistrationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureRegistrationServiceTest {

    @Mock
    private LectureRegistrationRepository lectureRegistrationRepository;

    @InjectMocks
    private LectureRegistrationService lectureRegistrationService;

    @DisplayName("이미 해당 특강 신청내역이 존재하면 예외가 발생한다.")
    @Test
    void throwException_when_already_applied() {
        String lectureId = "lecture1";
        String userId = "sid1";

        when(lectureRegistrationRepository.getLectureRegistrationInfo(lectureId, userId))
                .thenReturn(new LectureRegistrationInfo(lectureId, userId, LocalDateTime.now()));

        assertThatThrownBy(() -> lectureRegistrationService.createLectureRegistration(lectureId, userId))
                .isInstanceOf(LectureRegistrationException.class)
                .extracting(e -> ((LectureRegistrationException) e).getErrorResponse().code(),
                            e -> ((LectureRegistrationException) e).getErrorResponse().message())
                .containsExactly(LectureRegistrationError.DUPLICATED_LECTURE_REGISTRATION.getErrorResponse().code(),
                                 LectureRegistrationError.DUPLICATED_LECTURE_REGISTRATION.getErrorResponse().message());
    }

    @DisplayName("특정 사용자의 특강 신청 완료된 목록을 조회한다.")
    @Test
    void searchUserLectureRegistration() {
        String userId = "sid1";

        when(lectureRegistrationRepository.getAllLectureRegistrationInfoByUser(userId))
                .thenReturn(List.of(new LectureRegistrationInfo("lectureId1", "sid1", LocalDateTime.now()), new LectureRegistrationInfo("lectureId2", "sid2", LocalDateTime.now())));

        List<LectureRegistrationInfo> items = lectureRegistrationService.getAllLectureRegistrationByUser(userId);

        assertThat(items.size()).isEqualTo(2);
    }


}