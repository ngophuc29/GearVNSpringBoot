package com.gearvn.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gearvn.entities.LapTop;
import com.gearvn.repository.DienThoaiRepository;
import com.gearvn.service.DienThoaiService;

 

@Service
public class DienThoaiServiceImpl implements DienThoaiService {
    @Autowired
    DienThoaiRepository repository;

    @Override
    public LapTop findByMaDienThoai(long maDienThoai) {
        return repository.findByMaDienThoai(maDienThoai);
    }

}
