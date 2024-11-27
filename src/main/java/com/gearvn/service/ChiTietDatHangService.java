package com.gearvn.service;

import java.util.List;

import com.gearvn.entities.ChiTietNhapHang;
import com.gearvn.entities.NhaCungCap;

 

public interface ChiTietDatHangService {
    ChiTietNhapHang save(ChiTietNhapHang chiTietNhapHang);

    List<ChiTietNhapHang> findByNhaCungCap(NhaCungCap nhaCungCap);

    void deleteBymaSanPham(Long id);

    boolean deletingByMaSanPham_thenDeletingSanPham(Long id);

    ChiTietNhapHang findByMaSanPhamAndMaNhaCungCap(long idsp, long idncc);

    ChiTietNhapHang update(ChiTietNhapHang chiTietNhapHang);

    List<ChiTietNhapHang> findByGiaNhapBetween(double max, double min);

	ChiTietNhapHang findMotByNhaCungCap(NhaCungCap nhaCungCap);
}
