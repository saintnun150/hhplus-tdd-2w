package org.lowell.lectureregistration.domain.lectureRegistration;

public class LectureRegistrationCommand {

    public record Register(String lectureId, String userId) { }
}
