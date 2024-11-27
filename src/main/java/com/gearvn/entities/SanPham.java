package com.gearvn.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_san_pham")
    private long maSanPham;

    private String tenSanPham;

    @ManyToOne 
    @JoinColumn(name = "ma_loai_san_pham")
    @JsonIgnoreProperties("sanPhams")
    private LoaiSanPham loaiSanPham;

    @ManyToOne
    @JoinColumn(name = "ma_thuong_hieu")
    @JsonIgnoreProperties("sanPhams")
    private ThuongHieu thuongHieu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_hinh_anh")
    @JsonIgnoreProperties("sanPhams")
    private HinhAnh hinhAnh;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maLapTop") 
    @JsonIgnoreProperties("sanPhams")
    private LapTop lapTop;  // Đảm bảo tên biến chính xác

    @OneToMany(mappedBy = "sanPham")
    @JsonManagedReference
    private List<ChiTietNhapHang> chiTietNhapHangs;

    // Tính toán giá bán
    public double tinhGiaBan() {
        double giaBan = 0;
        if (chiTietNhapHangs != null && !chiTietNhapHangs.isEmpty()) {
            ChiTietNhapHang chiTietNhapHang = chiTietNhapHangs.get(0);
            giaBan = chiTietNhapHang.getDonGiaNhap() + chiTietNhapHang.getChiPhiLuuKho()
                    + chiTietNhapHang.getChiPhiQuanLy()
                    + chiTietNhapHang.getDonGiaNhap() * chiTietNhapHang.getPhanTramLoiNhuan();
        }
        return giaBan;
    }

    public SanPham() {
        super();
    }

    public SanPham(long maSanPham, String tenSanPham, LoaiSanPham loaiSanPham, ThuongHieu thuongHieu, HinhAnh hinhAnh,
                   LapTop lapTop, List<ChiTietNhapHang> chiTietNhapHangs) {
        super();
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.thuongHieu = thuongHieu;
        this.hinhAnh = hinhAnh;
        this.lapTop = lapTop;  // Sử dụng đúng tên biến
        this.chiTietNhapHangs = chiTietNhapHangs;
    }

    // Getters and Setters
    public long getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(long maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public HinhAnh getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(HinhAnh hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LapTop getLapTop() {
        return lapTop;  // Đảm bảo tên getter phù hợp
    }

    public void setLapTop(LapTop lapTop) {
        this.lapTop = lapTop;  // Đảm bảo tên setter phù hợp
    }

    public List<ChiTietNhapHang> getChiTietNhapHangs() {
        return chiTietNhapHangs;
    }

    public void setChiTietNhapHangs(List<ChiTietNhapHang> chiTietNhapHangs) {
        this.chiTietNhapHangs = chiTietNhapHangs;
    }
}
