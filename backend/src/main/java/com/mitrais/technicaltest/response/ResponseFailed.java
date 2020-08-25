package com.mitrais.technicaltest.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ResponseFailed<T> {
    private Date timestamp;
    private Integer status;
    private String message;
    private String error;
}
