
package com.gearvn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gearvn.entities.ChiTietNhapHang;
import com.gearvn.entities.LapTop;
import com.gearvn.entities.HinhAnh;
import com.gearvn.entities.LoaiSanPham;
import com.gearvn.entities.NhaCungCap;
import com.gearvn.entities.SanPham;
import com.gearvn.entities.ThuongHieu;
import com.gearvn.request.SaveNhaCungCapRequest;
import com.gearvn.request.SaveSanPhamRequest;
import com.gearvn.request.SaveThuongHieuRequest;
import com.gearvn.serviceimpl.ChiTietDatHangServiceimpl;
import com.gearvn.serviceimpl.LoaiSanPhamServiceImpl;
import com.gearvn.serviceimpl.NhaCungCapServiceImpl;
import com.gearvn.serviceimpl.SanPhamServiceImpl;
import com.gearvn.serviceimpl.ThuongHieuServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	NhaCungCapServiceImpl nhaCungCapService;

	@Autowired
	ChiTietDatHangServiceimpl chiTietDatHangService;

	@Autowired
	ThuongHieuServiceImpl thuongHieuCapService;

	@Autowired
	SanPhamServiceImpl sanPhamServiceImpl;

	@Autowired
	LoaiSanPhamServiceImpl loaiSanPhamServiceImpl;

	@Autowired
	private Environment environment;

	// Lấy danh sách sản phẩm
	@GetMapping("/sanpham/danh-sach-san-pham")
	public ResponseEntity<Map<String, Object>> getAllSanPhams() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<SanPham> sanPhams = sanPhamServiceImpl.findAll();
			response.put("status", HttpStatus.OK.value());
			response.put("data", sanPhams);
			response.put("error", null);
		} catch (Exception e) {
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// Trang thêm sản phẩm
	@GetMapping("/sanpham/them-san-pham")
	public ResponseEntity<Map<String, Object>> getPageThemSanPham() {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", "success");
			response.put("data",
					Map.of("nccs", nhaCungCapService.findAll(), "thuongHieus", thuongHieuCapService.findAll()));
			response.put("error", null);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/sanpham/them-san-pham")
	public ResponseEntity<Map<String, Object>> themSanPham(@RequestBody SaveSanPhamRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Kiểm tra và thêm nhà cung cấp
			System.out.println("tên nhà cung cấp " + request.getTenNCC());
			NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(request.getTenNCC());
			if (nhaCungCap != null) {
				System.out.println("Tìm thấy nhà cung cấp: " + nhaCungCap.getTenNhaCungCap());
				// Set lại thông tin nhà cung cấp nếu tìm thấy
				nhaCungCap.setDiaChi(Optional.ofNullable(request.getDiaChiNCC()).map(String::trim).orElse(""));
				nhaCungCap.setEmail(Optional.ofNullable(request.getEmailNCC()).map(String::trim).orElse(""));
				nhaCungCapService.save(nhaCungCap); // Cập nhật lại thông tin nhà cung cấp
			} else {
				System.out.println("Không tìm thấy nhà cung cấp, thêm mới");
				nhaCungCap = new NhaCungCap();
				nhaCungCap.setTenNhaCungCap(Optional.ofNullable(request.getTenNCC()).map(String::trim).orElse(""));
				nhaCungCap.setDiaChi(Optional.ofNullable(request.getDiaChiNCC()).map(String::trim).orElse(""));
				nhaCungCap.setEmail(Optional.ofNullable(request.getEmailNCC()).map(String::trim).orElse(""));
				nhaCungCapService.save(nhaCungCap); // Lưu nhà cung cấp mới
			}

			// Kiểm tra và thêm thương hiệu
			System.out.println("tên thương hiệu " + request.getTenThuongHieu());
			ThuongHieu thuongHieu = thuongHieuCapService.findByTenThuongHieu(request.getTenThuongHieu());
			if (thuongHieu != null) {
				System.out.println("Tìm thấy thương hiệu: " + thuongHieu.getTenThuongHieu());
				thuongHieu
						.setTenThuongHieu(Optional.ofNullable(request.getTenThuongHieu()).map(String::trim).orElse(""));
				thuongHieuCapService.saveThuongHieu(thuongHieu); // Cập nhật lại thông tin thương hiệu
			} else {
				System.out.println("Không tìm thấy thương hiệu, thêm mới");
				thuongHieu = new ThuongHieu();
				thuongHieu
						.setTenThuongHieu(Optional.ofNullable(request.getTenThuongHieu()).map(String::trim).orElse(""));
				thuongHieuCapService.saveThuongHieu(thuongHieu); // Lưu thương hiệu mới
			}

			// Kiểm tra và thêm loại sản phẩm
			System.out.println("tên loại sản phẩm " + request.getTenHangMuc());
			LoaiSanPham loaiSanPham = loaiSanPhamServiceImpl.findLoaiSanPhamByTenLoaiSanPham(request.getTenHangMuc());
			if (loaiSanPham != null) {
				System.out.println("Tìm thấy loại sản phẩm: " + loaiSanPham.getTenLoaiSanPham());
				loaiSanPham
						.setTenLoaiSanPham(Optional.ofNullable(request.getTenHangMuc()).map(String::trim).orElse(""));
				loaiSanPhamServiceImpl.saveLoaiSanPham(loaiSanPham); // Cập nhật lại thông tin loại sản phẩm
			} else {
				System.out.println("Không tìm thấy loại sản phẩm, thêm mới");
				loaiSanPham = new LoaiSanPham();
				loaiSanPham
						.setTenLoaiSanPham(Optional.ofNullable(request.getTenHangMuc()).map(String::trim).orElse(""));
				loaiSanPhamServiceImpl.saveLoaiSanPham(loaiSanPham); // Lưu loại sản phẩm mới
			}

			// Thêm thông tin laptop
			LapTop lapTop = new LapTop();
			lapTop.setTenLapTop(Optional.ofNullable(request.getTenSanPham()).map(String::trim).orElse(""));
			lapTop.setHeDieuHanh(Optional.ofNullable(request.getHeDieuHanh()).map(String::trim).orElse(""));
			lapTop.setManHinh(Optional.ofNullable(request.getManHinh()).map(String::trim).orElse(""));

			// Kiểm tra và chuyển kiểu dữ liệu cho các trường số
			try {
				lapTop.setRam(parseInt(request.getRam(), "RAM"));
				lapTop.setRom(parseInt(request.getRom(), "ROM"));
				lapTop.setPin(parseInt(request.getPin(), "PIN"));
				lapTop.setTrongLuong(parseDouble(request.getTrongLuong(), "weight"));
			} catch (NumberFormatException e) {
				response.put("status", "error");
				response.put("data", null);
				response.put("error", e.getMessage());
				return ResponseEntity.ok(response);
			}

			lapTop.setCpu(Optional.ofNullable(request.getCpu()).map(String::trim).orElse(""));
			lapTop.setCardDoHoa(Optional.ofNullable(request.getCardDoHoa()).map(String::trim).orElse(""));
			lapTop.setMauSac(Optional.ofNullable(request.getMauSac()).map(String::trim).orElse(""));
			lapTop.setLoaiPin(Optional.ofNullable(request.getLoaiPin()).map(String::trim).orElse(""));
			lapTop.setTinhNangKhac(Optional.ofNullable(request.getTinhNangKhac()).map(String::trim).orElse(""));

			// Lưu hình ảnh sản phẩm
			HinhAnh image = new HinhAnh();
			image.setUrl(Optional.ofNullable(request.getHinhAnh()).map(String::trim).orElse(""));

			// Tạo sản phẩm
			SanPham sanPham = new SanPham();
			sanPham.setTenSanPham(Optional.ofNullable(request.getTenSanPham()).map(String::trim).orElse(""));
			sanPham.setThuongHieu(thuongHieu);
			sanPham.setLoaiSanPham(loaiSanPham);
			sanPham.setLapTop(lapTop); // Liên kết sản phẩm với laptop
			sanPham.setHinhAnh(image);

			// Thêm thông tin nhập hàng
			ChiTietNhapHang chiTietNhapHang = new ChiTietNhapHang();
			chiTietNhapHang.setNhaCungCap(nhaCungCap);
			chiTietNhapHang.setDonGiaNhap(parseDouble(request.getDonGiaNhapNCC(), "purchase price"));
			chiTietNhapHang.setChiPhiLuuKho(parseDouble(request.getChiphiLuuKho(), "storage cost"));
			chiTietNhapHang.setChiPhiQuanLy(parseDouble(request.getChiPhiQuanLy(), "management cost"));
			chiTietNhapHang.setPhanTramLoiNhuan(parseDouble(request.getPhantramloinhan(), "profit margin"));
			chiTietNhapHang.setSanPham(sanPham);

			// Lưu thông tin nhập hàng và sản phẩm
			chiTietDatHangService.save(chiTietNhapHang);
			List<ChiTietNhapHang> chiTietNhapHangs = new ArrayList<>();
			chiTietNhapHangs.add(chiTietNhapHang);
			sanPham.setChiTietNhapHangs(chiTietNhapHangs);
			sanPhamServiceImpl.save(sanPham);

			response.put("status", "success");
			response.put("data", sanPham);
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	// Helper methods for parsing numbers
	private int parseInt(String value, String fieldName) {
		try {
			return Integer.parseInt(value != null && !value.trim().isEmpty() ? value.trim() : "0");
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid value for " + fieldName);
		}
	}

	private double parseDouble(String value, String fieldName) {
		try {
			return Double.parseDouble(value != null && !value.trim().isEmpty() ? value.trim() : "0");
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid value for " + fieldName);
		}
	}

	@PostMapping("/nhacungcap/them")
	public ResponseEntity<Map<String, Object>> themNhaCungCap(@RequestBody SaveNhaCungCapRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, Object> result = nhaCungCapService.findOrCreateNhaCungCap(request.getTenNCC(),
					request.getDiaChi(), request.getEmail());

			response.put("status", "success");
			response.put("data", result.get("nhaCungCap"));
			response.put("isUpdated", result.get("isUpdated"));
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/nhacungcap/capnhat/{id}")
	public ResponseEntity<Map<String, Object>> capNhatNhaCungCap(@PathVariable Long id,
			@RequestBody SaveNhaCungCapRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Gọi service cập nhật thông tin nhà cung cấp
			Map<String, Object> result = nhaCungCapService.updateNhaCungCapById(id, request.getTenNCC(),
					request.getDiaChi(), request.getEmail());

			response.put("status", "success");
			response.put("data", result.get("nhaCungCap"));
			response.put("isUpdated", result.get("isUpdated"));
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/nhacungcap/xoa/{id}")
	public ResponseEntity<Map<String, Object>> xoaNhaCungCap(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			boolean isDeleted = nhaCungCapService.deleteNhaCungCapById(id);

			if (isDeleted) {
				response.put("status", "success");
				response.put("message", "Nhà cung cấp đã bị xóa thành công.");
			} else {
				response.put("status", "error");
				response.put("message", "Không tìm thấy nhà cung cấp.");
			}
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "Xóa thất bại.");
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	// Tìm kiếm nhà cung cấp
	@PostMapping("/sanpham/nhacungcap")
	public ResponseEntity<Map<String, Object>> getNhaCungCap(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			String nameNcc = request.getParameter("nameNcc").trim();
			NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(nameNcc);

			if (nhaCungCap == null) {
				response.put("status", "error");
				response.put("data", null);
				response.put("error", "Nha Cung Cap not found");
			} else {
				nhaCungCap.setChiTietNhapHangs(chiTietDatHangService.findByNhaCungCap(nhaCungCap));
				response.put("status", "success");
				response.put("data", nhaCungCap);
				response.put("error", null);
			}
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

//	// Xóa sản phẩm
//	@DeleteMapping("/sanpham/delete")
//	public ResponseEntity<Map<String, Object>> deleteSanpham(@RequestParam long id) {
//		Map<String, Object> response = new HashMap<>();
//		try {
//			chiTietDatHangService.deleteBymaSanPham(id);
//			sanPhamServiceImpl.deleteBymaSanPham(id);
//			response.put("status", "success");
//			response.put("data", "Product deleted successfully");
//			response.put("error", null);
//		} catch (Exception e) {
//			response.put("status", "error");
//			response.put("data", null);
//			response.put("error", e.getMessage());
//		}
//		return ResponseEntity.ok(response);
//	}

	// Xem thông tin sản phẩm
	@GetMapping("/sanpham/view")
	public ResponseEntity<Map<String, Object>> getSanPhamById(@RequestParam long id_sp, @RequestParam String ten_ncc) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (id_sp == 0 && ten_ncc.equals("")) {
				response.put("status", "error");
				response.put("data", null);
				response.put("error", "Invalid parameters");
			} else {
				SanPham sanPham = sanPhamServiceImpl.findBymaSanPham(id_sp);
				NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(ten_ncc);
				ChiTietNhapHang chiTietNhapHang = chiTietDatHangService
						.findByMaSanPhamAndMaNhaCungCap(sanPham.getMaSanPham(), nhaCungCap.getMaNhaCungCap());
				response.put("status", "success");
				response.put("data", chiTietNhapHang);
				response.put("error", null);
			}
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	// Lấy danh sách sản phẩm
	@GetMapping("/sanpham/thuonghieu")
	public ResponseEntity<Map<String, Object>> getAllThuongHieu() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<ThuongHieu> thuonghieu = thuongHieuCapService.findAll();
			response.put("status", HttpStatus.OK.value());
			response.put("data", thuonghieu);
			response.put("error", thuonghieu);
		} catch (Exception e) {
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// API thêm hoặc tìm thương hiệu
	@PostMapping("/sanpham/thuonghieu/them")
	public ResponseEntity<Map<String, Object>> themThuongHieu(@RequestBody SaveThuongHieuRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Gọi service để tìm hoặc tạo thương hiệu
			Map<String, Object> result = thuongHieuCapService.findOrCreateThuongHieu(request.getTenThuongHieu());

			response.put("status", "success");
			response.put("data", result.get("thuongHieu"));
			response.put("isCreated", result.get("isCreated"));
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	// API cập nhật thương hiệu theo ID
	@PutMapping("/sanpham/thuonghieu/capnhat/{id}")
	public ResponseEntity<Map<String, Object>> capNhatThuongHieu(@PathVariable Long id,
			@RequestBody SaveThuongHieuRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Gọi service để cập nhật thông tin thương hiệu
			Map<String, Object> result = thuongHieuCapService.updateThuongHieuById(id, request.getTenThuongHieu());

			response.put("status", "success");
			response.put("data", result.get("thuongHieu"));
			response.put("isUpdated", result.get("isUpdated"));
		} catch (Exception e) {
			response.put("status", "error");
			response.put("data", null);
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	// API xóa thương hiệu theo ID
	@DeleteMapping("/sanpham/thuonghieu/xoa/{id}")
	public ResponseEntity<Map<String, Object>> xoaThuongHieu(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			boolean isDeleted = thuongHieuCapService.deleteThuongHieuById(id);

			if (isDeleted) {
				response.put("status", "success");
				response.put("message", "Thương hiệu đã bị xóa thành công.");
			} else {
				response.put("status", "error");
				response.put("message", "Không tìm thấy thương hiệu.");
			}
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "Xóa thất bại.");
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/sanpham/update/{id}")
	public ResponseEntity<Map<String, Object>> updateSanPham(@PathVariable Long id,
	        @RequestBody SaveSanPhamRequest request) {

	    Map<String, Object> response = new HashMap<>();
	    try {
	        // Kiểm tra và tạo NhaCungCap nếu cần thiết
	        NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(request.getTenNCC());
	        if (nhaCungCap == null) {
	            nhaCungCap = new NhaCungCap();
	            nhaCungCap.setTenNhaCungCap(request.getTenNCC());
	            nhaCungCap.setDiaChi(request.getDiaChiNCC());
	            nhaCungCap.setEmail(request.getEmailNCC());
	            nhaCungCapService.save(nhaCungCap);
	        }

	        System.out.println("nha cung cap selected : "+ nhaCungCap);
	        // Kiểm tra và tạo ThuongHieu nếu cần thiết
	        ThuongHieu thuongHieu = thuongHieuCapService.findByTenThuongHieu(request.getTenThuongHieu());
	        if (thuongHieu == null) {
	            thuongHieu = new ThuongHieu();
	            thuongHieu.setTenThuongHieu(request.getTenThuongHieu());
	            thuongHieuCapService.saveThuongHieu(thuongHieu);
	        }

	        // Kiểm tra và tạo LoaiSanPham nếu cần thiết
	        LoaiSanPham loaiSanPham = loaiSanPhamServiceImpl.findLoaiSanPhamByTenLoaiSanPham(request.getTenHangMuc());
	        if (loaiSanPham == null) {
	            loaiSanPham = new LoaiSanPham();
	            loaiSanPham.setTenLoaiSanPham(request.getTenHangMuc());
	            loaiSanPhamServiceImpl.saveLoaiSanPham(loaiSanPham);
	        }

	        // Kiểm tra và cập nhật sản phẩm
	        SanPham sanPham = sanPhamServiceImpl.findBymaSanPham(id);
	        System.out.println("san pham selected : "+sanPham.getMaSanPham());
	        if (sanPham != null) {
	            // Cập nhật các thuộc tính của sản phẩm
	            sanPham.setTenSanPham(request.getTenSanPham());
	            sanPham.setThuongHieu(thuongHieu);
	            sanPham.setLoaiSanPham(loaiSanPham);
	            
	            // Lưu các thông tin chi tiết sản phẩm
	            sanPham.getLapTop().setHeDieuHanh(request.getHeDieuHanh());
	            sanPham.getLapTop().setManHinh(request.getManHinh());
	            sanPham.getHinhAnh().setUrl(request.getHinhAnh()); // Cần phải tạo đối tượng HinhAnh nếu chưa có
	            sanPham.getLapTop().setRam(Integer.parseInt(request.getRam()));
	            sanPham.getLapTop().setRom(Integer.parseInt(request.getRom()));
	            sanPham.getLapTop().setCpu(request.getCpu());
	            sanPham.getLapTop().setCardDoHoa(request.getCardDoHoa());
	            sanPham.getLapTop().setPin(Integer.parseInt(request.getPin()));
	            sanPham.getLapTop().setTrongLuong(Double.parseDouble(request.getTrongLuong()));
	            sanPham.getLapTop().setMauSac(request.getMauSac());
	            sanPham.getLapTop().setLoaiPin(request.getLoaiPin());
	            sanPham.getLapTop().setTinhNangKhac(request.getTinhNangKhac());
	            
	            
	            ChiTietNhapHang ctnhaphang=chiTietDatHangService.findByMaSanPhamAndMaNhaCungCap(sanPham.getMaSanPham(), nhaCungCap.getMaNhaCungCap());
	            if (ctnhaphang == null) {
	                // Create a new ChiTietNhapHang if it doesn't exist
	                ctnhaphang = new ChiTietNhapHang();
	                ctnhaphang.setSanPham(sanPham);
	                ctnhaphang.setNhaCungCap(nhaCungCap);
	            }
	            // Các chi phí khác
	            ctnhaphang.setChiPhiLuuKho(Double.parseDouble(request.getChiphiLuuKho()));
	            ctnhaphang.setChiPhiQuanLy(Double.parseDouble(request.getChiPhiQuanLy()));
	            ctnhaphang.setPhanTramLoiNhuan(Double.parseDouble(request.getPhantramloinhan()));
	            ctnhaphang.setDonGiaNhap(Double.parseDouble(request.getDonGiaNhapNCC()));

	            // Cập nhật đối tượng LapTop nếu có (Giả định rằng LapTop có thể được cập nhật)
	            // sanPham.setLapTop(lapTopService.findById(request.getLapTopId())); // nếu có id LapTop

	            chiTietDatHangService.save(ctnhaphang);
	            sanPhamServiceImpl.save(sanPham);
	            response.put("status", "success");
	            response.put("data", sanPham); // Trả về sản phẩm đã cập nhật
	            response.put("error", null);
	        } else {
	            response.put("status", "error");
	            response.put("data", null);
	            response.put("error", "Sản phẩm không tồn tại.");
	        }
	    } catch (Exception e) {
	        // Log lỗi để kiểm tra chi tiết
	        e.printStackTrace();
	        response.put("status", "error");
	        response.put("data", null);
	        response.put("error", "Có lỗi xảy ra: " + e.getMessage());
	    }
	    return ResponseEntity.ok(response);
	}

	 

	@DeleteMapping("/sanpham/xoa/{id}")
	public ResponseEntity<Map<String, Object>> xoaSanPham(@PathVariable Long id) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        boolean isDeleted = sanPhamServiceImpl.deleteBymaSanPhamById(id);

	        if (isDeleted) {
	            response.put("status", "success");
	            response.put("message", "Sản phẩm đã bị xóa thành công.");
	        } else {
	            response.put("status", "error");
	            response.put("message", "Không tìm thấy sản phẩm.");
	        }
	    } catch (Exception e) {
	        response.put("status", "error");
	        response.put("message", "Xóa thất bại.");
	        response.put("error", e.getMessage());
	    }
	    return ResponseEntity.ok(response);
	}

}
