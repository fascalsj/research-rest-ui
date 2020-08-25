package com.mitrais.technicaltest.execption;

import com.mitrais.technicaltest.response.ResponseFailed;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@Slf4j
@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseFailed> handleIllegalArgumentation(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(500)
                .body(ResponseFailed.<UserOutboundDto>builder()
                .status(500)
                .timestamp(new Date(System.currentTimeMillis()))
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(JDBCException.class)
    public ResponseEntity<ResponseFailed> handleJDBCException(JDBCException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(500)
                .body(ResponseFailed.<UserOutboundDto>builder()
                        .status(500)
                        .timestamp(new Date(System.currentTimeMillis()))
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseFailed> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(500)
                .body(ResponseFailed.<UserOutboundDto>builder()
                        .status(500)
                        .timestamp(new Date(System.currentTimeMillis()))
                        .error(ex.getMessage())
                        .build());
    }


}

