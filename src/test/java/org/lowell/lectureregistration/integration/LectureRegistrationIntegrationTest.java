package org.lowell.lectureregistration.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lowell.lectureregistration.application.LectureFacade;
import org.lowell.lectureregistration.application.LectureRegistrationFacade;
import org.lowell.lectureregistration.application.StudentFacade;
import org.lowell.lectureregistration.application.TeacherFacade;
import org.lowell.lectureregistration.domain.lecture.LectureCommand;
import org.lowell.lectureregistration.domain.lectureRegistration.LectureRegistrationInfo;
import org.lowell.lectureregistration.domain.student.StudentCommand;
import org.lowell.lectureregistration.domain.student.StudentInfo;
import org.lowell.lectureregistration.domain.teacher.TeacherCommand;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LectureRegistrationIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(LectureRegistrationIntegrationTest.class);

    @Autowired
    private TeacherFacade teacherFacade;

    @Autowired
    private StudentFacade studentFacade;

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private LectureRegistrationFacade lectureRegistrationFacade;


    @DisplayName("선착순 30명 이후 신청자의 경우 수강신청에 실패한다.")
    @Test
    void applyLectureRegistration() throws InterruptedException {
        String lectureId = "lecture_1";

        createDefaultLecture();
        createDefaultTeacher();
        List<StudentInfo> students = createDefaultStudent();

        // 성공 및 실패 횟수
        int threadCnt = 40;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCnt);
        CountDownLatch latch = new CountDownLatch(threadCnt);

        for (int i = 0; i < threadCnt; i++) {
            String userId = students.get(i).userId();
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

        List<LectureRegistrationInfo> items = lectureRegistrationFacade.getAllLectureRegistrations(lectureId);
        assertThat(items.size()).isEqualTo(30);

    }

    private void executeConcurrentTasks(int threadCnt, Runnable task) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCnt);
        CountDownLatch latch = new CountDownLatch(threadCnt);

        for(int i = 0; i < threadCnt; ++i) {
            executorService.submit(() -> {
                try {
                    task.run();
                } catch (Exception e) {
                    log.error("## executor thread error[{}]", e.getMessage(), e);
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }

    private void createDefaultLecture(){
        String lectureId = "lecture_1";
        lectureFacade.createLecture(new LectureCommand.Create(lectureId, LocalDateTime.now().with(LocalTime.MIN), "teacher_1"));
    }

    private void createDefaultTeacher(){
        // 강사 등록
        for (int i = 1; i <= 3 ; i++) {
            String teacherId = "teacher_" + i;
            teacherFacade.createTeacher(new TeacherCommand.Create(teacherId));
        }
    }

    private List<StudentInfo> createDefaultStudent(){
        List<StudentInfo> studentInfos = new ArrayList<>();
        // 학생 등록
        for (int i = 0; i < 50; i++) {
            String studentId = "student_" + i;
            StudentInfo student = studentFacade.createStudent(new StudentCommand.Create(studentId));
            studentInfos.add(student);
        }
        return studentInfos;
    }
}
