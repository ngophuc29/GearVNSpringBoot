package com.gearvn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearvn.entities.LoaiSanPham;

 
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
    LoaiSanPham findLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham);
    // Tự động hỗ trợ findAll() để lấy tất cả dữ liệu
    List<LoaiSanPham> findAll();
}
