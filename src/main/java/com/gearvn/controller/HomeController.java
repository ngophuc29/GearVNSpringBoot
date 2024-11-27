package com.gearvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gearvn.entities.Account;
import com.gearvn.repository.SanPhamRepository;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final SanPhamRepository repository = null;

    @GetMapping
    public Map<String, Object> home(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            response.put("status", "success");
            response.put("data", repository.findAll());

            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                response.put("account", account);
            } else {
                response.put("account", null);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }
}
