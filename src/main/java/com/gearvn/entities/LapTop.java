package com.gearvn.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "lap_top")
public class LapTop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_lap_top")
    private long maLapTop;

    private String tenLapTop;  // Tên laptop
    private String manHinh;  // Kích thước màn hình
    private String heDieuHanh;  // Hệ điều hành
    private int ram;  // Dung lượng RAM
    private int rom;  // Dung lượng ổ cứng (SSD hoặc HDD)
    private String cpu;  // Thông tin về vi xử lý
    private String cardDoHoa;  // Card đồ họa
    private int pin;  // Dung lượng pin (mAh hoặc Wh)
    private double trongLuong;  // Trọng lượng laptop (kg)
    private String mauSac;  // Màu sắc của laptop
    private String loaiPin;  // Loại pin (ví dụ: Lithium-ion, Lithium-polymer)
    private String tinhNangKhac;  // Các tính năng đặc biệt (ví dụ: cảm biến vân tay, màn hình cảm ứng)

    @OneToMany(mappedBy = "lapTop")
    @JsonManagedReference
    private List<SanPham> sanPhams;

    public LapTop() {
        super();
    }

    public LapTop(long maLapTop, String tenLapTop, String manHinh, String heDieuHanh, int ram, int rom, String cpu, String cardDoHoa, int pin, double trongLuong, String mauSac, String loaiPin, String tinhNangKhac, List<SanPham> sanPhams) {
        super();
        this.maLapTop = maLapTop;
        this.tenLapTop = tenLapTop;
        this.manHinh = manHinh;
        this.heDieuHanh = heDieuHanh;
        this.ram = ram;
        this.rom = rom;
        this.cpu = cpu;
        this.cardDoHoa = cardDoHoa;
        this.pin = pin;
        this.trongLuong = trongLuong;
        this.mauSac = mauSac;
        this.loaiPin = loaiPin;
        this.tinhNangKhac = tinhNangKhac;
        this.sanPhams = sanPhams;
    }

    public LapTop(String tenLapTop, String manHinh, String heDieuHanh, int ram, int rom, String cpu, String cardDoHoa,
			int pin, double trongLuong, String mauSac, String loaiPin, String tinhNangKhac, List<SanPham> sanPhams) {
		super();
		this.tenLapTop = tenLapTop;
		this.manHinh = manHinh;
		this.heDieuHanh = heDieuHanh;
		this.ram = ram;
		this.rom = rom;
		this.cpu = cpu;
		this.cardDoHoa = cardDoHoa;
		this.pin = pin;
		this.trongLuong = trongLuong;
		this.mauSac = mauSac;
		this.loaiPin = loaiPin;
		this.tinhNangKhac = tinhNangKhac;
		this.sanPhams = sanPhams;
	}

	// Getters and Setters
    public long getMaLapTop() {
        return maLapTop;
    }

    public void setMaLapTop(long maLapTop) {
        this.maLapTop = maLapTop;
    }

    public String getTenLapTop() {
        return tenLapTop;
    }

    public void setTenLapTop(String tenLapTop) {
        this.tenLapTop = tenLapTop;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCardDoHoa() {
        return cardDoHoa;
    }

    public void setCardDoHoa(String cardDoHoa) {
        this.cardDoHoa = cardDoHoa;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(double trongLuong) {
        this.trongLuong = trongLuong;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getLoaiPin() {
        return loaiPin;
    }

    public void setLoaiPin(String loaiPin) {
        this.loaiPin = loaiPin;
    }

    public String getTinhNangKhac() {
        return tinhNangKhac;
    }

    public void setTinhNangKhac(String tinhNangKhac) {
        this.tinhNangKhac = tinhNangKhac;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
