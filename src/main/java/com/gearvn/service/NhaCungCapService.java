package com.gearvn.service;

import java.util.List;
import java.util.Map;

import com.gearvn.entities.NhaCungCap;

 
 

public interface NhaCungCapService {

    List<NhaCungCap> findAll();

    boolean existsByTenNhaCungCap(String tenNhaCungCap);

    NhaCungCap save(NhaCungCap nhaCungCap);

    NhaCungCap findByTenNhaCungCap(String tenNhaCungCap);
    Map<String, Object> findOrCreateNhaCungCap(String tenNCC, String diaChi, String email);

}
