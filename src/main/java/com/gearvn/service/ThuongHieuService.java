package com.gearvn.service;

import java.util.List;

import com.gearvn.entities.SanPham;
import com.gearvn.entities.ThuongHieu;

 

public interface ThuongHieuService {
    List<ThuongHieu> findAll();

    ThuongHieu findByTenThuongHieu(String tenThuongHieu);

    ThuongHieu saveThuongHieu(ThuongHieu thuongHieu);

    List<SanPham> findByThuongHieu(ThuongHieu thuongHieu);
}
