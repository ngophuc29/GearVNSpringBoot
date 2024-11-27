package com.gearvn.serviceimpl;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gearvn.entities.Account;
import com.gearvn.repository.UserRepository;
import com.gearvn.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


    
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<Account>customer=repository.findByUsername(username);
		
		 
		String password=null;
		
		List<GrantedAuthority>authorities=null;
		
		if(customer.isEmpty()) {
			throw new UsernameNotFoundException("K tim thay customer "+ username);
		}
		 
		username=customer.get(0).getUsername();
		password=customer.get(0).getPassword();
		
		authorities =new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole().toString()));
		
				
		return new User(username, password, authorities);
	}
	
    
	@Override
	public List<Account> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account registerCustomer(Account account) {
		return repository.save(account);
	}

    @Override
    public Account save(Account account) {
    	account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }
}

