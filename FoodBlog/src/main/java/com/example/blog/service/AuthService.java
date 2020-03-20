package com.example.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.repository.UserRepository;
import com.example.blog.security.UserPrincipal;
import com.example.blog.util.Busers;

@Service
public class AuthService implements UserDetailsService{

	 @Autowired
	    UserRepository userRepository;

	    @Transactional
	    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

	        Busers user = userRepository.findByEmail(email);
//	                .orElseThrow(() -> new UsernameNotFoundException("email Not Found with -> email : " + email));
	        System.out.println(user.getEmail() + "   " + email);
	        return UserPrincipal.build(user);
	    }

	    
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    	Busers user = userRepository.findByEmail(email);
//	                .orElseThrow(() -> new UsernameNotFoundException("username Not Found with -> username : " + username));
	        System.out.println(user.getEmail() + " " + email);
	        return UserPrincipal.build(user);
	    }
	
}
