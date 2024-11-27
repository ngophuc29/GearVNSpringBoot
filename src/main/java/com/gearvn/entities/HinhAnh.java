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
@Table(name = "hinh_anh")
public class HinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hinh_anh")
    private long maHinhAnh;

    private String url;

    @OneToMany(mappedBy = "hinhAnh")
    @JsonManagedReference
    private List<SanPham> sanPhams;

	public HinhAnh(long maHinhAnh, String url, List<SanPham> sanPhams) {
		super();
		this.maHinhAnh = maHinhAnh;
		this.url = url;
		this.sanPhams = sanPhams;
	}

	public HinhAnh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getMaHinhAnh() {
		return maHinhAnh;
	}

	public void setMaHinhAnh(long maHinhAnh) {
		this.maHinhAnh = maHinhAnh;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}
    
    
}
