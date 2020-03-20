package com.example.blog.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.repository.UserRepository;
//import com.example.blog.security.UserPrincipal;
import com.example.blog.util.Blogs;
import com.example.blog.util.Busers;
import com.example.blog.util.Comments;
import com.example.blog.util.ResponseCreator;


@Service
public class UserService  {
	
	@PersistenceContext
	EntityManager entitymanager;
	
	@Autowired
	UserRepository userRepo;

	public ResponseEntity<String> getAllUser() {
		
		try {
			List<Busers> s= userRepo.findAll();
			System.out.println("getc all:"+s);
			return ResponseEntity.ok(ResponseCreator.responseBlogUsers("User retrieved successfully", s, 200));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogUsers("Server error ! cannot retrieve user details",null,500));
			
		}
	}

	public ResponseEntity<String> newUser(Busers user) {
		
		try {
			
			Busers u = new Busers();
			Busers uemail = new Busers();
			
			uemail = userRepo.findByEmail(user.getEmail());
			System.out.println(uemail);
			
			if(uemail != null) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogUsers("Error ! user already exist with same email address",null,500));
			}
			else {
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
				String cryptoPassword = passwordEncoder.encode(user.getPassword());
				
				
//				u.setBlogs(blogs);
				u.setEmail(user.getEmail());
				u.setUserName(user.getUserName());
				u.setPassword(cryptoPassword);
				userRepo.save(u);
				
				return ResponseEntity.ok(ResponseCreator.responseBlogUsers("User created successfully", null, 200));
			
			
		}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogUsers("Server error ! cannot create user",null,500));
			
		}
		
	}

	public ResponseEntity<String> deleteUser(int userId) {
	
		try {
			Busers user = new Busers();
			
			
			Optional<Busers> uId = userRepo.findById(userId);
			
			if(uId == null) {
				
				return ResponseEntity.ok(ResponseCreator.responseBlogUsers("cannot find user !", null, 200));
				
			}
			else {

				user.setUserId(userId);
				
				userRepo.delete(user);
			
			return ResponseEntity.ok(ResponseCreator.responseBlogUsers("deleted one userInfo  successfully !", null, 200));
			}
			
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogUsers("Server error! cannot delete userInfo", null, 500));
			}
	}

	public ResponseEntity<String> login(String email, String password) {
		try {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
				
//			long phone = 0;
//			String email = null;
			Busers useremail = new Busers();
			System.out.println(userRepo.findByEmail(email));
			useremail = userRepo.findByEmail(email);
			
			if(useremail == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogs("Server error ! cannot find user",null,500));
			}
			else {
				System.out.println("Name is: "+useremail.getEmail());
				System.out.println("Password is: "+useremail.getPassword());
				
				String input_password = useremail.getPassword();
				
				if(BCrypt.checkpw(password, input_password)){
//				
				return ResponseEntity.ok(ResponseCreator.responseBlogs("user logged in successfully !",null,200));
//				
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
							.responseBlogs("incorrect password",null,500));
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogs("Server error ! cannot find data",null,500));
			}
	}

	public ResponseEntity<String> createUserWithBlog(int blogId, Busers user) {
		
		try {
		
		Blogs b = entitymanager.find(Blogs.class, blogId);
		Busers u = new Busers();
		
		
		Busers uemail = new Busers();
		
		uemail = userRepo.findByEmail(user.getEmail());
		System.out.println(uemail);
		
		if(uemail != null) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogUsers("Error ! user already exist with same email address",null,500));
		}
		else {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			String cryptoPassword = passwordEncoder.encode(user.getPassword());
			
			
//			u.setBlogs(b);
			u.setEmail(user.getEmail());
			u.setUserName(user.getUserName());
			u.setPassword(cryptoPassword);
			userRepo.save(u);
			
			return ResponseEntity.ok(ResponseCreator.responseBlogUsers("User created successfully", null, 200));
		
		}
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("Server error ! cannot create user",null,500));
			
		}
	}
	


	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
