package com.gearvn.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import com.gearvn.entities.Account;
import com.gearvn.serviceimpl.SanPhamServiceImpl;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    SanPhamServiceImpl sanPhamServiceImpl;

    @GetMapping
    public ResponseEntity<?> searchProduct(HttpServletRequest request, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        String searchVal = request.getParameter("searchVal");
        
        try {
            // Kiểm tra nếu không có kết quả tìm kiếm
            if (sanPhamServiceImpl.findByTenSanPhamContaining(searchVal).isEmpty()) {
                response.put("status", "success");
                response.put("message", "No products found");
                response.put("data", "null");
            } else {
                response.put("status", "success");
                response.put("data", sanPhamServiceImpl.findByTenSanPhamContaining(searchVal));
            }
            
            // Kiểm tra nếu có thông tin người dùng
            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                response.put("account", account);
            } else {
                response.put("username", null);
            }

            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
