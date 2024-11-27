package com.gearvn.entities;

import java.util.*;

import jakarta.persistence.*;


 
@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_gio_hang")
    private long maGioHang;

    private Date ngayTao;

    private boolean trangThaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "id")
    private Account account;

    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL)
    private List<ChiTietGioHang> chiTietGioHangs = new ArrayList<>();

	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GioHang(long maGioHang, Date ngayTao, boolean trangThaiThanhToan, Account account,
			List<ChiTietGioHang> chiTietGioHangs) {
		super();
		this.maGioHang = maGioHang;
		this.ngayTao = ngayTao;
		this.trangThaiThanhToan = trangThaiThanhToan;
		this.account = account;
		this.chiTietGioHangs = chiTietGioHangs;
	}

	public long getMaGioHang() {
		return maGioHang;
	}

	public void setMaGioHang(long maGioHang) {
		this.maGioHang = maGioHang;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public boolean isTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<ChiTietGioHang> getChiTietGioHangs() {
		return chiTietGioHangs;
	}

	public void setChiTietGioHangs(List<ChiTietGioHang> chiTietGioHangs) {
		this.chiTietGioHangs = chiTietGioHangs;
	}

}
