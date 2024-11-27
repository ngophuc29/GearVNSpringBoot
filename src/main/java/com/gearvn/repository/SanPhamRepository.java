package com.gearvn.repository;

 
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gearvn.entities.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete san_pham where ma_san_pham=?1", nativeQuery = true)
    void deleteBymaSanPham(Long id);

    SanPham findBymaSanPham(Long id);

    // tim kiem ten theo chuoi con
    List<SanPham> findByTenSanPhamContaining(String tenSanPham);

}
