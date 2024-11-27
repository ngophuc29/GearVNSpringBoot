package com.gearvn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearvn.entities.SanPham;
import com.gearvn.entities.ThuongHieu;

 

public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {
    // find by tenThuongHieu
    ThuongHieu findByTenThuongHieu(String tenThuongHieu);

    // tim kiem san pham theo ten thuong hieu
    List<SanPham> findByTenThuongHieu(ThuongHieu thuongHieu);
}
