package org.lowell.lectureregistration.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lowell.lectureregistration.application.LectureRegistrationFacade;
import org.lowell.lectureregistration.domain.lecture.LectureCommand;
import org.lowell.lectureregistration.domain.lecture.LectureRepository;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.domain.student.StudentInfo;
import org.lowell.lectureregistration.domain.student.StudentRepository;
import org.lowell.lectureregistration.domain.teacher.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LectureRegistrationIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(LectureRegistrationIntegrationTest.class);

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureRegistrationFacade lectureRegistrationFacade;

    @BeforeEach
    void setUp() {
        createDefaultLecture();
        createDefaultTeacher();
        createDefaultStudent();
    }

    private void createDefaultLecture(){
        String lectureId = "lecture_1";
        LocalDateTime applyDate = LocalDateTime.now().with(LocalTime.MIN);
        lectureRepository.insert(lectureId, applyDate);
    }

    private void createDefaultTeacher(){
        String teacherId = "teacher_1";
        teacherRepository.insert(teacherId);
    }

    private List<StudentInfo> createDefaultStudent(){
        List<StudentInfo> infoList = new ArrayList<>();
        // 학생 등록
        for (int i = 0; i < 50; i++) {
            String studentId = "student_" + i;
            StudentInfo save = studentRepository.insert(studentId);
            infoList.add(save);
        }
        return infoList;
    }

    @DisplayName("선착순 30명 이후 신청자의 경우 수강신청에 실패한다.")
    @Test
    void applyLectureRegistration() throws InterruptedException {
        String lectureId = "lecture_1";
        // 수강 신청 시도 횟수
        int threadCnt = 40;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCnt);
        CountDownLatch latch = new CountDownLatch(threadCnt);

        List<String> userIds = studentRepository.getAllStudentInfo()
                                                .stream()
                                                .map(StudentInfo::userId)
                                                .toList();

        for (int i = 0; i < threadCnt; i++) {
            String userId = userIds.get(i);
            executorService.submit(() -> {
                try {
                    lectureRegistrationFacade.applyLectureRegistration(lectureId, userId);
                } catch (Exception e) {
                    log.error("## executor thread error[{}]", e.getMessage(), e);
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        List<LectureRegistrationInfo> items = lectureRegistrationFacade.getAllLectureRegistrations(new LectureCommand.SearchLectureRequest(lectureId, null));
        assertThat(items.size()).isEqualTo(30);

    }

    @DisplayName("이미 신청한 강의일 경우 예외가 발생한다.")
    @Test
    void throwException_when_already_applied_lecture() throws InterruptedException {
        String lectureId = "lecture_1";
        String userId = "student_1";
        // 수강 신청 시도 횟수
        int threadCnt = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCnt);
        CountDownLatch latch = new CountDownLatch(threadCnt);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        for (int i = 0; i < threadCnt; i++) {
            executorService.submit(() -> {
                try {
                    lectureRegistrationFacade.applyLectureRegistration(lectureId, userId);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    log.error("## executor error[{}]", e.getMessage(), e);
                    failCount.incrementAndGet();
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        assertThat(successCount.get()).isEqualTo(1);
        assertThat(failCount.get()).isEqualTo(4);

    }
}
