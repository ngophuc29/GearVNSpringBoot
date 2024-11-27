package com.gearvn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NewsController {

    @GetMapping("/news")
    public ResponseEntity<Map<String, Object>> news() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            response.put("status", "success");
            response.put("data", "public/News");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
