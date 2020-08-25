package com.mitrais.technicaltest.execption;

import com.mitrais.technicaltest.response.ResponseFailed;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@Slf4j
@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseFailed> handleIllagalArgumentation(IllegalArgumentException ex) {
        log.warn("Validation API exception caught");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseFailed.<UserOutboundDto>builder()
                .status(400)
                .timestamp(new Date(System.currentTimeMillis()))
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(JDBCException.class)
    public ResponseEntity<ResponseFailed> handleJDBCException(JDBCException ex) {
        log.warn("Validation API exception caught");

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ResponseFailed.<UserOutboundDto>builder()
                        .status(406)
                        .timestamp(new Date(System.currentTimeMillis()))
                        .error(ex.getMessage())
                        .build());
    }
}

