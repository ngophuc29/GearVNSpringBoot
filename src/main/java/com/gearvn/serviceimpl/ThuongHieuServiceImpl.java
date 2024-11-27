package com.gearvn.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearvn.entities.SanPham;
import com.gearvn.entities.ThuongHieu;
import com.gearvn.repository.ThuongHieuRepository;
import com.gearvn.service.ThuongHieuService;



@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> findAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu findByTenThuongHieu(String tenThuongHieu) {
        return thuongHieuRepository.findByTenThuongHieu(tenThuongHieu);
    }

    @Override
    public ThuongHieu saveThuongHieu(ThuongHieu thuongHieu) {
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public List<SanPham> findByThuongHieu(ThuongHieu thuongHieu) {
        return thuongHieuRepository.findByTenThuongHieu(thuongHieu);
    }
    
    
    public Map<String, Object> findOrCreateThuongHieu(String tenThuongHieu) {
        if (tenThuongHieu == null || tenThuongHieu.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên thương hiệu không được để trống.");
        }

        // Kiểm tra xem thương hiệu đã tồn tại chưa
        ThuongHieu thuongHieu = thuongHieuRepository.findByTenThuongHieu(tenThuongHieu.trim());
        if (thuongHieu != null) {
            // Nếu đã tồn tại, chỉ thông báo và không tạo mới
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Thương hiệu đã tồn tại.");
            response.put("isCreated", false);
            response.put("status", "error");
            return response;
        }

        // Nếu chưa tồn tại, tạo mới thương hiệu
        thuongHieu = new ThuongHieu();
        thuongHieu.setTenThuongHieu(tenThuongHieu.trim());

        // Lưu vào cơ sở dữ liệu nếu chưa tồn tại
        ThuongHieu savedThuongHieu = thuongHieuRepository.save(thuongHieu);
        Map<String, Object> response = new HashMap<>();
        response.put("thuongHieu", savedThuongHieu);
        response.put("isCreated", true);

        return response;
    }



    
    public Map<String, Object> updateThuongHieuById(Long id, String ten) {
        Map<String, Object> result = new HashMap<>();
        
        // Tìm thương hiệu theo ID
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepository.findById(id);
        if (thuongHieuOptional.isPresent()) {
            ThuongHieu thuongHieu = thuongHieuOptional.get();
            thuongHieu.setTenThuongHieu(ten);
            
            // Lưu thông tin cập nhật
            thuongHieuRepository.save(thuongHieu);
            result.put("thuongHieu", thuongHieu);
            result.put("isUpdated", true);
        } else {
            result.put("isUpdated", false);
            result.put("message", "Thương hiệu không tồn tại.");
        }
        
        return result;
    }

    public boolean deleteThuongHieuById(Long id) {
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepository.findById(id);
        if (thuongHieuOptional.isPresent()) {
            thuongHieuRepository.deleteById(id);
            return true;
        }
        return false; // Trả về false nếu thương hiệu không tồn tại
    }

}
