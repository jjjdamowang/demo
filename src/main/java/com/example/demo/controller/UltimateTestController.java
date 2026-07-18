package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UltimateTestController {

    @GetMapping("/ultra/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/ultra/test")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("message", "Controller is working");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}