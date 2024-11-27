package com.gearvn.controller;

 

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.gearvn.entities.Account;
import com.gearvn.request.AuthenticationRequest;
import com.gearvn.request.RegisterRequest;
import com.gearvn.response.AuthenticationResponse;
import com.gearvn.service.AuthenticationService;
import com.gearvn.serviceimpl.UserServiceImpl;

@Controller
@RequiredArgsConstructor
 
public class AuthenticationController {

    private final AuthenticationService service = new AuthenticationService();

    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "public/Login";
    }
//
//    @GetMapping("/register")
//    public String openRegister() {
//        return "public/SignUp";
//    }

//    @PostMapping("/register")
//    public ResponseEntity<ResponseEntity<AuthenticationResponse>> register(@RequestBody RegisterRequest request) {
//        Account account = userServiceImpl.findByEmail(request.getEmail()).orElse(null);
//        if (account != null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<ResponseEntity<AuthenticationResponse>> login(@RequestBody AuthenticationRequest authenticationRequest) {
//        return ResponseEntity.ok(service.authenticate(authenticationRequest));
//    }
    @PostMapping("/register")
	public ResponseEntity<String>registerCustomer(
			@RequestBody Account customer
			){
		
		ResponseEntity<String>response= null;
		try {
			Account saveCus= userServiceImpl.save(customer);
			
			if(saveCus.getId()>0) {
				response=ResponseEntity.status(HttpStatus.CREATED)
						.body("Customer is created successfully for customer ");
			}
		} catch (Exception e) {
			response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred from server!!"+ e);
		}
		return response;
		 
	}
}
