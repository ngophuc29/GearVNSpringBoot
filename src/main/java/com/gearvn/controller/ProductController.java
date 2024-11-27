
package com.gearvn.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.gearvn.entities.Account;
import com.gearvn.entities.Breadcrumb;
import com.gearvn.entities.LoaiSanPham;
import com.gearvn.entities.NhaCungCap;
import com.gearvn.entities.SanPham;
import com.gearvn.entities.ThuongHieu;
import com.gearvn.serviceimpl.ChiTietDatHangServiceimpl;
import com.gearvn.serviceimpl.LoaiSanPhamServiceImpl;
import com.gearvn.serviceimpl.NhaCungCapServiceImpl;
import com.gearvn.serviceimpl.SanPhamServiceImpl;
import com.gearvn.serviceimpl.ThuongHieuServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/san-pham")
public class ProductController {
    @Autowired
    private SanPhamServiceImpl repository;
    @Autowired
    private LoaiSanPhamServiceImpl LoaiSanPhamrepository;

    @Autowired
    ChiTietDatHangServiceimpl chiTietDatHangService;

    @Autowired
    ThuongHieuServiceImpl thuongHieuService;

    @Autowired
    NhaCungCapServiceImpl nhaCungService;
    
    @SuppressWarnings("unused")
	@Autowired
    private Environment environment;

    @SuppressWarnings("unused")
    @GetMapping("/laptop")
    public ResponseEntity<?> list(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<SanPham> sanphams = repository.findAll();
            String breadcrumb = "Laptop";
            String href = "/san-pham/laptop";
            Account account = null;
            String userName = null;

            if (principal != null) {
                account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }

            response.put("status", "success");
            response.put("data", new Object() {
                public final List<SanPham> sanphams = repository.findAll();
                public final String breadcrumb = "laptop";
                public final String href = "/san-pham/laptop";
                public final Account account = principal != null ? (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
                public final String username = principal == null ? userName : null;
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    
    @GetMapping("/category")
    public ResponseEntity<?> listcategory(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
//            List<SanPham> sanphams = repository.findAll();
        	 List<LoaiSanPham> loaisanPham = LoaiSanPhamrepository.findAll();
            String breadcrumb = "Laptop";
            String href = "/san-pham/category";
            Account account = null;
            String userName = null;

            if (principal != null) {
                account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }

            response.put("status", "success");
            response.put("data", new Object() {
                public final  List<LoaiSanPham> loaisanPham = LoaiSanPhamrepository.findAll();
                public final String breadcrumb = "laptop";
                public final String href = "/san-pham/category";
                public final Account account = principal != null ? (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
                public final String username = principal == null ? userName : null;
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    @GetMapping("/nhacungcap")
    public ResponseEntity<?> listNhaCungCap(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
//            List<SanPham> sanphams = repository.findAll();
        	 List<NhaCungCap> nhacungcap = nhaCungService.findAll();
            String breadcrumb = "Laptop";
            String href = "/san-pham/nhacungcap";
            Account account = null;
            String userName = null;

            if (principal != null) {
                account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }

            response.put("status", "success");
            response.put("data", new Object() {
                public final   List<NhaCungCap> nhacungcap = nhaCungService.findAll();
                public final String breadcrumb = "laptop";
                public final String href = "/san-pham/nhacungcap";
                public final Account account = principal != null ? (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
                public final String username = principal == null ? userName : null;
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    
    
    @GetMapping("/thuonghieu")
    public ResponseEntity<?> listThuongHieu(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
//            List<SanPham> sanphams = repository.findAll();
        	 List<ThuongHieu> thuonghieu = thuongHieuService.findAll();
            String breadcrumb = "Laptop";
            String href = "/san-pham/thuonghieu";
            Account account = null;
            String userName = null;

            if (principal != null) {
                account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }

            response.put("status", "success");
            response.put("data", new Object() {
                public final  List<ThuongHieu> thuonghieu = thuongHieuService.findAll();
                public final String breadcrumb = "laptop";
                public final String href = "/san-pham/thuonghieu";
                public final Account account = principal != null ? (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
                public final String username = principal == null ? userName : null;
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    
    
    @GetMapping("/laptop/show")
    public ResponseEntity<?> searchProductPost(@RequestParam("id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            SanPham product = repository.findBymaSanPham(Long.parseLong(id));
            List<SanPham> sanphams = repository.findAll();
            List<Breadcrumb> breadcrumblink = new ArrayList<>();
            breadcrumblink.add(new Breadcrumb("Laptop", "/san-pham/laptop"));
            breadcrumblink.add(new Breadcrumb(product.getTenSanPham(), "/san-pham/laptop/show?id=" + product.getMaSanPham()));

            response.put("status", "success");
            response.put("data", new Object() {
                public final List<SanPham> sanphams = repository.findAll();
                public final SanPham product = repository.findBymaSanPham(Long.parseLong(id));
                public final List<Breadcrumb> breadcrumb = breadcrumblink;
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    
    @PostMapping("/filter")
    public ResponseEntity<?> filter(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String brand = request.getParameter("brand");
            Set<SanPham> set = new HashSet<>();
            if (brand.equals("all")) {
                response.put("status", "success");
                response.put("data", repository.findAll());
                return ResponseEntity.ok(response);
            }
            String[] arrBand = brand.split(",");
            for (String s : arrBand) {
                s = s.trim();
                if (!s.equals(" ")) {
                    set.addAll(thuongHieuService.findByTenThuongHieu(s).getSanPhams());
                }
            }
            response.put("status", "success");
            response.put("data", set);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> refreshFilter() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Redirecting to /san-pham/laptop");
        return ResponseEntity.ok(response);
    }

}
