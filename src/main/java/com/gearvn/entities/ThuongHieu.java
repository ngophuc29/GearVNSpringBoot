package com.gearvn.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 

 
@Entity
@Table(name = "thuong_hieu")
public class ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_thuong_hieu")
    private long maThuongHieu;

    private String tenThuongHieu;

    @OneToMany(mappedBy = "thuongHieu")
    @JsonManagedReference
    private List<SanPham> sanPhams;

	public ThuongHieu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThuongHieu(long maThuongHieu, String tenThuongHieu, List<SanPham> sanPhams) {
		super();
		this.maThuongHieu = maThuongHieu;
		this.tenThuongHieu = tenThuongHieu;
		this.sanPhams = sanPhams;
	}

	public long getMaThuongHieu() {
		return maThuongHieu;
	}

	public void setMaThuongHieu(long maThuongHieu) {
		this.maThuongHieu = maThuongHieu;
	}

	public String getTenThuongHieu() {
		return tenThuongHieu;
	}

	public void setTenThuongHieu(String tenThuongHieu) {
		this.tenThuongHieu = tenThuongHieu;
	}

	public List<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}
    
    
}
