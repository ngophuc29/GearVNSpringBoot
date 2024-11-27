package com.gearvn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gearvn.entities.ChiTietNhapHang;
import com.gearvn.entities.NhaCungCap;

import jakarta.transaction.Transactional;

public interface ChiTietDatHangRepository extends JpaRepository<ChiTietNhapHang, Long> {
    
    // Lấy thông tin đơn giá nhập theo mã nhà cung cấp
    List<ChiTietNhapHang> findByNhaCungCap(NhaCungCap nhaCungCap);

    // Xóa bản ghi theo mã sản phẩm
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM chi_tiet_nhap_hang WHERE ma_san_pham = ?1", nativeQuery = true)
    void deleteBymaSanPham(Long id);

    // Tìm thông tin chi tiết nhập hàng theo mã sản phẩm và mã nhà cung cấp
    @Query(value = "SELECT * FROM chi_tiet_nhap_hang WHERE ma_san_pham = ?1 AND ma_nha_cung_cap = ?2", nativeQuery = true)
    ChiTietNhapHang findByMaSanPhamAndMaNhaCungCap(long idsp, long idncc);

    // Tìm các sản phẩm có giá nhập nằm trong khoảng (min, max)
    List<ChiTietNhapHang> findByDonGiaNhapBetween(double max, double min);
}
