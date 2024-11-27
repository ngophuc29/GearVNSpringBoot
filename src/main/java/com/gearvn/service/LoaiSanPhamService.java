package com.gearvn.service;

import java.util.List;

import com.gearvn.entities.LoaiSanPham;
import com.gearvn.entities.SanPham;

public interface LoaiSanPhamService {

	 List<LoaiSanPham> findAll();
    LoaiSanPham saveLoaiSanPham(LoaiSanPham loaiSanPham);

    LoaiSanPham findLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham);
}
