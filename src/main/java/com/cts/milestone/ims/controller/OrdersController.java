package com.cts.milestone.ims.controller;

import com.cts.milestone.ims.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrdersController {


    @Autowired
    private RestTemplate restTemplate;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        ResponseEntity<List<Order>> orderResp = restTemplate.exchange("http://order-service/v1/orders/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> orders = orderResp.getBody();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAllOrdersForProduct/{productId}")
    public ResponseEntity<List<Order>> getAllOrdersForProduct(@PathVariable String productId) {
        ResponseEntity<List<Order>> orderResp = restTemplate.exchange("http://order-service/v1/orders/all/"+productId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                });
        List<Order> orders = orderResp.getBody();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello User!";
    }

}
