package com.gearvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gearvn.entities.LapTop;

 

public interface DienThoaiRepository extends JpaRepository<LapTop, Long> {

    @Query(value = "select * from dien_thoai where ma_san_pham=?1", nativeQuery = true)
    LapTop findByMaDienThoai(long maDienThoai);
}
