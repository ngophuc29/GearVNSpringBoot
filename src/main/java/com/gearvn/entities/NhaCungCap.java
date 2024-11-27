package com.gearvn.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 

 
@Entity
@Table(name = "nha_cung_cap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nha_cung_cap")
    private long maNhaCungCap;

    @Column(name = "ten_nha_cung_cap", columnDefinition = "NVARCHAR(255)")
    private String tenNhaCungCap;

    private String diaChi;

    private String email;

    @OneToMany(mappedBy = "nhaCungCap")
    @JsonIgnore
    private List<ChiTietNhapHang> chiTietNhapHangs;

    public long getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(long maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ChiTietNhapHang> getChiTietNhapHangs() {
		return chiTietNhapHangs;
	}

	public void setChiTietNhapHangs(List<ChiTietNhapHang> chiTietNhapHangs) {
		this.chiTietNhapHangs = chiTietNhapHangs;
	}

	public NhaCungCap(long maNhaCungCap, String tenNhaCungCap, String diaChi, String email,
			List<ChiTietNhapHang> chiTietNhapHangs) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.email = email;
		this.chiTietNhapHangs = chiTietNhapHangs;
	}

	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", diaChi=" + diaChi
                + ", email=" + email + "]";
    }

}
