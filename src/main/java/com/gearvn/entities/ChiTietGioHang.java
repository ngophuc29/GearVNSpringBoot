package com.gearvn.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
 

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 

 	
@Entity
@Table(name = "chi_tiet_gio_hang")
public class ChiTietGioHang {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_gio_hang")
    @JsonBackReference
    private GioHang gioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;

    private int soLuong;

    private double discount;

	public ChiTietGioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietGioHang(GioHang gioHang, SanPham sanPham, int soLuong, double discount) {
		super();
		this.gioHang = gioHang;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.discount = discount;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
    
    
}
