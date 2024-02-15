package com.example.web.controller;

import com.example.web.dto.ErrorResponse;
import com.example.web.exception.ErrorCode;
import com.example.web.exception.WebSampleException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
public class SampleController {
    @GetMapping(value = "/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) throws SQLIntegrityConstraintViolationException {
        log.info("Get some order information " + orderId);

        if ("500".equals(orderId)) {
            throw new WebSampleException(ErrorCode.TOO_BIG_ID_ERROR, "500 is too big id error");
        }

        if ("300".equals(orderId)) {
            throw new WebSampleException(ErrorCode.TOO_SMALL_ID_ERROR, "300 is too small id error");
        }

        if ("400".equals(orderId)) {
            throw new SQLIntegrityConstraintViolationException("400 is Duplicated insertion was tried");
        }

        return "orderId :" + orderId + " orderAmount = 100";
    }

    @DeleteMapping(value = "/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String orderId) {
        log.info("Delete some order information " + orderId);
        return "orderId :" + orderId;
    }

    @GetMapping(value = "/order")
    public String getOrderWithRequestParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String orderId,
            @RequestParam("orderAmount") Integer orderAmount) {
        log.info("Get some order information " + orderId + " orderAmount " + orderAmount);
        return "orderId :" + orderId + " orderAmount = " + orderAmount;
    }

    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("Create order " + createOrderRequest + " userAccountId " + userAccountId);
        return "orderId :" + createOrderRequest.getOrderId() + " orderAmount = " + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order1")
    public String createOrder() {
        log.info("Create Order");
        return "orderId = 2, orderAmount = 1000";
    }


    @Data
    public static class CreateOrderRequest {

        private String orderId;
        private Integer orderAmount;
    }
}
