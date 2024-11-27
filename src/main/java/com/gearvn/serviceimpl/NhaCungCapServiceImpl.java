package com.gearvn.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearvn.entities.NhaCungCap;
import com.gearvn.repository.NhaCungCapRepository;
import com.gearvn.service.NhaCungCapService;

 

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    @Override
    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findAll();
    }

    @Override
    public boolean existsByTenNhaCungCap(String tenNhaCungCap) {
        return nhaCungCapRepository.existsByTenNhaCungCap(tenNhaCungCap);
    }

    @Override
    public NhaCungCap save(NhaCungCap nhaCungCap) {
        return nhaCungCapRepository.save(nhaCungCap);
    }

    @Override
    public NhaCungCap findByTenNhaCungCap(String tenNhaCungCap) {
        return nhaCungCapRepository.findByTenNhaCungCap(tenNhaCungCap);
    }

    public Map<String, Object> findOrCreateNhaCungCap(String tenNCC, String diaChi, String email) {
        if (tenNCC == null || tenNCC.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên nhà cung cấp không được để trống.");
        }

        NhaCungCap nhaCungCap = nhaCungCapRepository.findByTenNhaCungCap(tenNCC.trim());
        boolean isUpdated = false;

        if (nhaCungCap != null) {
            // Cập nhật thông tin nếu đã tồn tại
            nhaCungCap.setDiaChi(Optional.ofNullable(diaChi).map(String::trim).orElse(""));
            nhaCungCap.setEmail(Optional.ofNullable(email).map(String::trim).orElse(""));
            isUpdated = true;
        } else {
            // Tạo mới nếu không tồn tại
            nhaCungCap = new NhaCungCap();
            nhaCungCap.setTenNhaCungCap(tenNCC.trim());
            nhaCungCap.setDiaChi(Optional.ofNullable(diaChi).map(String::trim).orElse(""));
            nhaCungCap.setEmail(Optional.ofNullable(email).map(String::trim).orElse(""));
        }

        NhaCungCap savedNhaCungCap = nhaCungCapRepository.save(nhaCungCap);
        Map<String, Object> response = new HashMap<>();
        response.put("nhaCungCap", savedNhaCungCap);
        response.put("isUpdated", isUpdated);

        return response;
    }
    
    
    public Map<String, Object> updateNhaCungCapById(Long id, String ten,String diaChi, String email) {
        Map<String, Object> result = new HashMap<>();
        
        // Tìm nhà cung cấp theo ID
        Optional<NhaCungCap> nhaCungCapOptional = nhaCungCapRepository.findById(id);
        if (nhaCungCapOptional.isPresent()) {
            NhaCungCap nhaCungCap = nhaCungCapOptional.get();
            nhaCungCap.setTenNhaCungCap(ten);
            nhaCungCap.setDiaChi(diaChi);
            nhaCungCap.setEmail(email);
            
            // Lưu thông tin cập nhật
            nhaCungCapRepository.save(nhaCungCap);
            result.put("nhaCungCap", nhaCungCap);
            result.put("isUpdated", true);
        } else {
            result.put("isUpdated", false);
        }
        
        return result;
    }

    public boolean deleteNhaCungCapById(Long id) {
        Optional<NhaCungCap> nhaCungCapOptional = nhaCungCapRepository.findById(id);
        if (nhaCungCapOptional.isPresent()) {
            nhaCungCapRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
