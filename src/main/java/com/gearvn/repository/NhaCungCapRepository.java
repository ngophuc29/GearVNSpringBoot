package com.gearvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gearvn.entities.NhaCungCap;

 

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {
    // exists tenNhaCungCap
    boolean existsByTenNhaCungCap(String tenNhaCungCap);

    // find by tenNhaCungCap
    NhaCungCap findByTenNhaCungCap(String tenNhaCungCap);

	 

}
