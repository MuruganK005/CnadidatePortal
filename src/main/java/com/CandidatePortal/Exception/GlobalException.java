package com.CandidatePortal.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    private Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(value =CandidateException.class)
    public ResponseEntity<?> handleTeamException(CandidateException exception) {
        logger.error("Admin exception: {}", exception.getErrorMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .message(exception.getErrorMessage()).build(),
                exception.getStatus());
    }
}
