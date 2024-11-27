package com.gearvn.entities;

import java.util.List;

import jakarta.persistence.*;
 

 
@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham {
    public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_loai_san_pham")
    private long maLoaiSanPham;

    private String tenLoaiSanPham;

    @OneToMany(mappedBy = "loaiSanPham")
    private List<SanPham> sanPhams;

    public long getMaLoaiSanPham() {
		return maLoaiSanPham;
	}

	public void setMaLoaiSanPham(long maLoaiSanPham) {
		this.maLoaiSanPham = maLoaiSanPham;
	}

	public String getTenLoaiSanPham() {
		return tenLoaiSanPham;
	}

	public void setTenLoaiSanPham(String tenLoaiSanPham) {
		this.tenLoaiSanPham = tenLoaiSanPham;
	}

	public List<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}

	public LoaiSanPham(long maLoaiSanPham, String tenLoaiSanPham, List<SanPham> sanPhams) {
		super();
		this.maLoaiSanPham = maLoaiSanPham;
		this.tenLoaiSanPham = tenLoaiSanPham;
		this.sanPhams = sanPhams;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoaiSanPham{");
        sb.append("maLoaiSanPham=").append(maLoaiSanPham);
        sb.append(", tenLoaiSanPham='").append(tenLoaiSanPham).append('\'');
        sb.append(", sanPhams=");
        if (sanPhams != null) {
            sb.append(sanPhams.size());
        } else {
            sb.append("null");
        }
        sb.append('}');
        return sb.toString();
    }
}
