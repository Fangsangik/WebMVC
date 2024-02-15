package com.example.web.exception;

import com.example.web.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException (IllegalAccessException e){
        log.error("Illegal Exception : ", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("newHeader", "some Value")
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR, "IllegalException is occured"));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {WebSampleException.class, RuntimeException.class}) //예외 처리를 많이해야 하는 경우 리스트로 처리 or 여러가지 Exception을 한번에 Handling 할 수 있도록
    public ResponseEntity<ErrorResponse> handleWebsampleException (WebSampleException e){
        log.error("WebSampleException Occured : ", e);
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
                .body(new ErrorResponse(e.getErrorCode(), "WebSampleException is occured"));
    }

    @ExceptionHandler(Exception.class) //어떤 에러가 와도 최후의 보류인 handleException으로 처리
    public ResponseEntity<ErrorResponse> handleException (Exception e){
        log.error("Exception : ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "IllegalException is occured"));
    }
}
