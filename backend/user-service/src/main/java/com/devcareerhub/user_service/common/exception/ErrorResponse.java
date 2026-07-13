package com.devcareerhub.user_service.common.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String message,
        String errorCode,
        List<String> details
) {
}
