package com.gearvn.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	//Allow all requests
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").permitAll() // Allow all requests
																								// without
																								// authentication
				.anyRequest().authenticated()).csrf(csrf -> csrf.disable()); // Disable CSRF protection if not needed
		return http.build();
	}

	
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Chỉ quyền ADMIN được truy cập
//	                .anyRequest().permitAll() // Các API khác không yêu cầu bảo mật
//	            )
//	            .httpBasic() // Dùng Basic Authentication
//	            .and()
//	            .csrf().disable(); // Tắt CSRF (nếu không cần)
//	        return http.build();
//	    }
	 
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}