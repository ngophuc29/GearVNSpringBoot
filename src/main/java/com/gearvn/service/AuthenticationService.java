
package com.gearvn.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.gearvn.entities.Account;
import com.gearvn.entities.Role;
import com.gearvn.request.AuthenticationRequest;
import com.gearvn.request.RegisterRequest;
import com.gearvn.response.AuthenticationResponse;
import com.gearvn.serviceimpl.UserServiceImpl;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationService {

    @Autowired
    UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder = null;
    private final JwtService jwtService = null;
    private final AuthenticationManager authenticationManager = null;

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        var user = Account.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//        userService.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return ResponseEntity.ok(AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build());
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = userService.findByEmail(request.getEmail()).orElseThrow(null);
//        var jwtToken = jwtService.generateToken(user);
//        return ResponseEntity.ok(AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build());
//    }

    
    @PostMapping("/register")
   	public ResponseEntity<String>registerCustomer(
   			@RequestBody Account customer
   			){
   		
   		ResponseEntity<String>response= null;
   		try {
   			Account saveCus= userService.save(customer);
   			
   			if(saveCus.getId()>0) {
   				response=ResponseEntity.status(HttpStatus.CREATED)
   						.body("Customer is created successfully for customer ");
   			}
   		} catch (Exception e) {
   			response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred from server!!"+ e);
   		}
   		return response;
   		 
   	}
    
   

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        jwtService.invalidate(token);
        return ResponseEntity.ok("Logout successfully");
    }
}
