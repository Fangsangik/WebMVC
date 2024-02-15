package com.example.web.dto;

import com.example.web.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
}
