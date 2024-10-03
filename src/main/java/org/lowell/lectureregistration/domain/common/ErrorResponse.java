package org.lowell.lectureregistration.domain.common;

public record ErrorResponse(
        String code,
        String message
) {
}
