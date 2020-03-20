package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.repository.NewUserRepo;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.ResponseCreator;
import com.example.ecommerce.util.Users;
import com.example.ecommerce.security.UserPrincipal;

@Service
public class UserService {

	
	@Autowired
	UserRepository uRepo;
	@Autowired
	NewUserRepo newRepo;
	@Autowired
	public JavaMailSender emailSender;
	
	int generated_otp = 0;
	
	public ResponseEntity<String> createUser( String name, String email, long phone, Boolean is_admin, String password) {
		// TODO Auto-generated method stub
		return uRepo.createUser(name,email,phone,is_admin,password);
	}

	public ResponseEntity<String> getAllUser() {
		// TODO Auto-generated method stub
		return uRepo.getAllUser();
	}
	
	
	public ResponseEntity<String> updateUser( int id,String name, String email, long phone, Boolean is_admin, String password) {
		// TODO Auto-generated method stub
		return uRepo.updateUser(id, name,email,phone,is_admin,password);
	}
	
	public ResponseEntity<String> deleteUserInfo(int id){
		return uRepo.deleteUserInfo(id);
	}
	
	
	
	
	public ResponseEntity<String> login(String name,String password){
		try {
			
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			
		long phone = 0;
		String email = null;
		Users username = new Users(name, phone, email, password);
		System.out.println(newRepo.findByName(name));
		username = newRepo.findByName(name);
		
		if(username == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot find user",null,500));
		}
		else {
			System.out.println("Name is: "+username.getName());
			System.out.println("Password is: "+username.getPassword());
			
			String input_password = username.getPassword();
			
			if(BCrypt.checkpw(password, input_password)){
			
			return ResponseEntity.ok(ResponseCreator.response("user logged in successfully !",null,200));
			
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.response("incorrect password",null,500));
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot find data",null,500));
		}	
	}

	public ResponseEntity<String> newUser(String name, String email, long phone, Boolean is_admin, String password) {
		// TODO Auto-generated method stub
		try {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			String cryptoPassword = passwordEncoder.encode(password);
			
		Users userObj = new Users(name, phone, email, cryptoPassword);
		userObj.setEmail(email);
		userObj.setIsAdmin(is_admin);
		userObj.setName(name);
		userObj.setPassword(cryptoPassword);
		userObj.setPhone(phone);
		
		newRepo.save(userObj);
		return ResponseEntity.ok(ResponseCreator.response("created user  successfully !",null,200));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot create user",null,500));
		}
	}

	public ResponseEntity<String> sendEmail(String email) {
		// TODO Auto-generated method stub
		try {
		
		String name = null;
		long phone = 0;
		String password = null;
		Users useremail = new Users(name, phone, email, password);
		
		useremail = newRepo.findByEmail(email);
		System.out.println("user email object:"+useremail);
		
		if(useremail == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot find user",null,500));
		}
		else {
			System.out.println("email is: "+useremail.getEmail());
			
			generated_otp = (int)(Math.random() * ((9999 - 1000) + 1)) + 1000;
			
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(email); 
	        message.setSubject("OTP from ashfi's java app"); 
	        message.setText("your Otp is: "+generated_otp);
	        emailSender.send(message);
			
	        return ResponseEntity.ok(ResponseCreator.response("email sent successfully", null, 200));
			
		}
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("User error ! invalid email address",null,500));
			
		}
		
		
//		return uRepo.sendEmail(email);
	}

	public ResponseEntity<String> verifyotp(int otp) {
		// TODO Auto-generated method stub
		try {
			if(otp != generated_otp) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.response("User error ! invalid Otp error",null,500));
			}
			else {	
				
			return ResponseEntity.ok(ResponseCreator.response("Otp verified successfully!", null, 200));
			
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("User error ! ",null,500));
		}
	}
	
	
	
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	@Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        Users user = newRepo.findByEmail(email);
        System.out.println(user.getEmail() + "   " + email);
        return UserPrincipal.build(user);
    }
	
	
}
