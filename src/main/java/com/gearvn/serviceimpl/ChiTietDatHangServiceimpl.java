package com.gearvn.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.gearvn.entities.ChiTietNhapHang;
import com.gearvn.entities.NhaCungCap;
import com.gearvn.repository.ChiTietDatHangRepository;
import com.gearvn.repository.SanPhamRepository;
import com.gearvn.service.ChiTietDatHangService;

import jakarta.transaction.Transactional;

 

@Service
public class ChiTietDatHangServiceimpl implements ChiTietDatHangService {
    @Autowired
    ChiTietDatHangRepository repository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public ChiTietNhapHang save(ChiTietNhapHang chiTietNhapHang) {
        return repository.save(chiTietNhapHang);
    }

    @Override
    public List<ChiTietNhapHang> findByNhaCungCap(NhaCungCap nhaCungCap) {
        return repository.findByNhaCungCap(nhaCungCap);
    }

    @Override
    @Transactional // Đảm bảo thao tác xóa được thực hiện trong một giao dịch
    @Modifying  // Thực hiện thao tác thay đổi dữ liệu
    public void deleteBymaSanPham(Long id) {
        repository.deleteBymaSanPham(id);  // Gọi phương thức xóa trong repository
    }

    @Override
    public boolean deletingByMaSanPham_thenDeletingSanPham(Long id) {
        return false;
    }

    @Override
    public ChiTietNhapHang findByMaSanPhamAndMaNhaCungCap(long idsp, long idncc) {
        return repository.findByMaSanPhamAndMaNhaCungCap(idsp, idncc);
    }

    @Override
    public ChiTietNhapHang update(ChiTietNhapHang chiTietNhapHang) {
        return repository.save(chiTietNhapHang);
    }

    @Override
    public List<ChiTietNhapHang> findByGiaNhapBetween(double max, double min) {
        return repository.findByDonGiaNhapBetween(max, min);
    }

    @Override
    public ChiTietNhapHang findMotByNhaCungCap(NhaCungCap nhaCungCap) {
        // Trả về kết quả đầu tiên hoặc null nếu không có
        return repository.findByNhaCungCap(nhaCungCap).stream().findFirst().orElse(null);
    }

}