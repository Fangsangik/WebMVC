package com.example.web.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

//custom error exception
public class WebSampleException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
}
