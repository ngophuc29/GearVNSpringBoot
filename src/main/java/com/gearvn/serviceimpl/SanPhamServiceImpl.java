package com.gearvn.serviceimpl;

 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearvn.entities.SanPham;
import com.gearvn.entities.ThuongHieu;
import com.gearvn.repository.SanPhamRepository;
import com.gearvn.service.SanPhamService;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository repository;
    
    @Autowired
    ChiTietDatHangServiceimpl ctnhrepository;
    

    @Override
    public SanPham save(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public List<SanPham> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteBymaSanPham(Long id) {
        repository.deleteBymaSanPham(id);
    }

    @Override
    public SanPham findBymaSanPham(Long id) {
        return repository.findBymaSanPham(id);
    }

    @Override
    public SanPham update(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public List<SanPham> findByTenSanPhamContaining(String tenSanPham) {
        return repository.findByTenSanPhamContaining(tenSanPham);
    }

    public boolean deleteBymaSanPhamById(Long id) {
        Optional<SanPham> sanpham = repository.findById(id);
        if (sanpham.isPresent()) {
            try {
                // Bước 1: Xóa các bản ghi liên quan trong bảng chi_tiet_nhap_hang
                ctnhrepository.deleteBymaSanPham(id);  // Xóa tất cả bản ghi liên quan có ma_san_pham = id

                // Bước 2: Xóa sản phẩm từ bảng san_pham
                repository.deleteById(id);

                return true; // Xóa thành công
            } catch (Exception e) {
                // Xử lý lỗi nếu có
                throw new RuntimeException("Không thể xóa sản phẩm: " + e.getMessage());
            }
        }
        return false; // Sản phẩm không tồn tại
    }


}
