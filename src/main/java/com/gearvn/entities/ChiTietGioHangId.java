package com.gearvn.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 

@Embeddable
public class ChiTietGioHangId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ma_gio_hang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;

    // Constructors, getters and setters
    public ChiTietGioHangId() {}

    public ChiTietGioHangId(GioHang gioHang, SanPham sanPham) {
        this.gioHang = gioHang;
        this.sanPham = sanPham;
    }

    // Getters and setters
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

    // hashCode and equals methods for composite key
    @Override
    public int hashCode() {
        return gioHang.hashCode() + sanPham.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChiTietGioHangId other = (ChiTietGioHangId) obj;
        return gioHang.equals(other.gioHang) && sanPham.equals(other.sanPham);
    }
}
