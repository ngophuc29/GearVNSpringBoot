package com.gearvn.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearvn.entities.LoaiSanPham;
import com.gearvn.repository.LoaiSanPhamRepository;
import com.gearvn.service.LoaiSanPhamService;

 

@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    @Autowired
    LoaiSanPhamRepository repository;

    @Override
    public LoaiSanPham findLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham) {
        return repository.findLoaiSanPhamByTenLoaiSanPham(tenLoaiSanPham);
    }

    @Override
    public LoaiSanPham saveLoaiSanPham(LoaiSanPham loaiSanPham) {
        return repository.save(loaiSanPham);
    }

	@Override
	public List<LoaiSanPham> findAll() {
		 return repository.findAll();
	}
}
