package com.example.web.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SampleController {
    @GetMapping(value = "/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) {
        log.info("Get some order information " + orderId);
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
