package com.gearvn.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
 

 
@Entity
@Table(name = "chi_tiet_nhap_hang")
public class ChiTietNhapHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maChiTietNhapHang;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_nha_cung_cap")
    @JsonIgnoreProperties("chiTietNhapHangs")
    private NhaCungCap nhaCungCap;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_san_pham")
    @JsonBackReference
    private SanPham sanPham;

    private double donGiaNhap;

    private double chiPhiLuuKho;

    private double chiPhiQuanLy;

    private double phanTramLoiNhuan;

	public ChiTietNhapHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietNhapHang(long maChiTietNhapHang, NhaCungCap nhaCungCap, SanPham sanPham, double donGiaNhap,
			double chiPhiLuuKho, double chiPhiQuanLy, double phanTramLoiNhuan) {
		super();
		this.maChiTietNhapHang = maChiTietNhapHang;
		this.nhaCungCap = nhaCungCap;
		this.sanPham = sanPham;
		this.donGiaNhap = donGiaNhap;
		this.chiPhiLuuKho = chiPhiLuuKho;
		this.chiPhiQuanLy = chiPhiQuanLy;
		this.phanTramLoiNhuan = phanTramLoiNhuan;
	}

	public long getMaChiTietNhapHang() {
		return maChiTietNhapHang;
	}

	public void setMaChiTietNhapHang(long maChiTietNhapHang) {
		this.maChiTietNhapHang = maChiTietNhapHang;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public double getDonGiaNhap() {
		return donGiaNhap;
	}

	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}

	public double getChiPhiLuuKho() {
		return chiPhiLuuKho;
	}

	public void setChiPhiLuuKho(double chiPhiLuuKho) {
		this.chiPhiLuuKho = chiPhiLuuKho;
	}

	public double getChiPhiQuanLy() {
		return chiPhiQuanLy;
	}

	public void setChiPhiQuanLy(double chiPhiQuanLy) {
		this.chiPhiQuanLy = chiPhiQuanLy;
	}

	public double getPhanTramLoiNhuan() {
		return phanTramLoiNhuan;
	}

	public void setPhanTramLoiNhuan(double phanTramLoiNhuan) {
		this.phanTramLoiNhuan = phanTramLoiNhuan;
	}

}
