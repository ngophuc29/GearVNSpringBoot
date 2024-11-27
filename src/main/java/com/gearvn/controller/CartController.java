package com.gearvn.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.gearvn.entities.Account;
import com.gearvn.entities.ChiTietGioHang;
import com.gearvn.entities.GioHang;
import com.gearvn.entities.SanPham;
import com.gearvn.repository.GioHangRepository;
import com.gearvn.repository.SanPhamRepository;

import java.security.Principal;
import java.util.*;

@RestController
@SessionAttributes("cart")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @PersistenceContext
    private EntityManager entityManager;

    
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestParam("id") Long productId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        if (productId == null) {
            response.put("status", "error");
            response.put("error", "Missing required parameter 'id'");
            return ResponseEntity.status(400).body(response);
        }

        // Kiểm tra xem giỏ hàng đã tồn tại trong session chưa
        GioHang cart = (GioHang) session.getAttribute("cart");

        if (cart == null) {
            // Tạo mới giỏ hàng chỉ khi chưa có giỏ hàng trong session
            cart = new GioHang();
            cart.setNgayTao(new Date());
            cart.setTrangThaiThanhToan(false);
            session.setAttribute("cart", cart);  // Lưu giỏ hàng vào session
            
            // Không cần gọi gioHangRepository.save(cart) ở đây
        }

        boolean isProductExists = false;
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            if (cartItem.getSanPham().getMaSanPham() == productId) {
                // Nếu sản phẩm đã có trong giỏ hàng, chỉ cần tăng số lượng
                cartItem.setSoLuong(cartItem.getSoLuong() + 1);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
            // Nếu sản phẩm chưa có trong giỏ hàng, tìm sản phẩm và thêm vào giỏ hàng
            Optional<SanPham> product = sanPhamRepository.findById(productId);
            if (product.isPresent()) {
                SanPham sanPham = product.get();
                ChiTietGioHang cartItem = new ChiTietGioHang();
                cartItem.setSanPham(sanPham);
                cartItem.setSoLuong(1);
                cartItem.setDiscount(sanPham.getChiTietNhapHangs().get(0).getDonGiaNhap()
                        + (sanPham.getChiTietNhapHangs().get(0).getChiPhiLuuKho()
                            + sanPham.getChiTietNhapHangs().get(0).getChiPhiQuanLy())
                        * sanPham.getChiTietNhapHangs().get(0).getPhanTramLoiNhuan());
                cart.getChiTietGioHangs().add(cartItem);
                // Lưu chi tiết giỏ hàng vào cơ sở dữ liệu
                cart.getChiTietGioHangs().add(cartItem);
            } else {
                response.put("status", "error");
                response.put("error", "Product not found");
                return ResponseEntity.status(404).body(response);
            }
        }

        response.put("status", "success");
        response.put("data", cart);
        return ResponseEntity.ok(response);
    }




    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutCart(HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("cart");
        Map<String, Object> response = new HashMap<>();
        
        if (cart == null || cart.getChiTietGioHangs().isEmpty()) {
            response.put("status", "error");
            response.put("error", "No items in cart");
            return ResponseEntity.status(400).body(response);
        }

        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            cartItem.setGioHang(cart);
        }
        gioHangRepository.save(cart);
        session.removeAttribute("cart");

        response.put("status", "success");
        response.put("data", "Checkout completed");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> showCart(HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession();
        GioHang cart = (GioHang) session.getAttribute("cart");
        Map<String, Object> response = new HashMap<>();

        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        double totalAmount = 0.0;
        for (ChiTietGioHang item : cart.getChiTietGioHangs()) {
            double itemPrice = (item.getSanPham().getChiTietNhapHangs().get(0).getDonGiaNhap()
                    + item.getSanPham().getChiTietNhapHangs().get(0).getChiPhiLuuKho()
                    + item.getSanPham().getChiTietNhapHangs().get(0).getChiPhiQuanLy())
                    * item.getSanPham().getChiTietNhapHangs().get(0).getPhanTramLoiNhuan();
            totalAmount += itemPrice;
        }

        response.put("status", "success");
        response.put("data", cart);
        response.put("totalAmount", totalAmount);

        if (principal != null) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            response.put("account", account);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/increase")
    public ResponseEntity<?> increase(@RequestParam("id") Long productId, HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("cart");
        Map<String, Object> response = new HashMap<>();

        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        boolean isProductExists = false;
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            if (cartItem.getSanPham().getMaSanPham() == productId) {
                cartItem.setSoLuong(cartItem.getSoLuong() + 1);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
            response.put("status", "error");
            response.put("error", "Product not found in cart");
            return ResponseEntity.status(404).body(response);
        }

        response.put("status", "success");
        response.put("data", cart);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/decrease")
    public ResponseEntity<?> decrease(@RequestParam("id") Long productId, HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("cart");
        Map<String, Object> response = new HashMap<>();

        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        boolean isProductExists = false;
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            if (cartItem.getSanPham().getMaSanPham() == productId) {
                cartItem.setSoLuong(cartItem.getSoLuong() - 1);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
            response.put("status", "error");
            response.put("error", "Product not found in cart");
            return ResponseEntity.status(404).body(response);
        }

        response.put("status", "success");
        response.put("data", cart);
        return ResponseEntity.ok(response);
    }
}
