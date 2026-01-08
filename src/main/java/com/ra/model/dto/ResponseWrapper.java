package com.ra.model.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseWrapper<T>{
    private boolean success;
    private String message;
    private T data;
    private Map<String, String> errors;
    private int httpStatus;
}
