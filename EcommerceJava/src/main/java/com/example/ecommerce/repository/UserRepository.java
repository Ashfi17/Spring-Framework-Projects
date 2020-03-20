package com.example.ecommerce.repository;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.ResponseCreator;
import com.example.ecommerce.util.Users;

@Transactional
@Repository
public class UserRepository {
	
	
	
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	public JavaMailSender emailSender;
	
	public ResponseEntity<String> createUser( String name, String email, long phone, Boolean is_admin, String password) {
		// TODO Auto-generated method stub
		try {
			
			Users user = new Users();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			String cryptoPassword = passwordEncoder.encode(password);
			
//			 user.setId(id);
			user.setPassword(cryptoPassword);
			user.setName(name);
			user.setEmail(email);
			user.setPhone(phone);
			user.setIsAdmin(is_admin);
	
//			System.out.println(id);
			System.out.println(cryptoPassword);
			System.out.println(name);
			System.out.println(email);
			System.out.println(phone);
			System.out.println(is_admin);
			
			entitymanager.persist(user);
			
			entitymanager.flush();
			
			return ResponseEntity.ok(ResponseCreator.response("one user info created",null,200));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator.response("data cannot be inserted", null, 400));
		}
	}



	public ResponseEntity<String> getAllUser() {
		// TODO Auto-generated method stub
		try {
			
			Query query = entitymanager.createNativeQuery("Select * from users");
			//to use key value pair
			((org.hibernate.query.Query<Object>)query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List<Object> s = ((org.hibernate.query.Query<Object>)query).list();
			
			return ResponseEntity.ok(ResponseCreator.response("data retrieved successfully", s, 200));
			
		}catch(Exception e) {
			System.out.println("Exception occured while fetching");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator.response("data cannot be inserted", null, 400));
		}
	}
	
	public ResponseEntity<String> updateUser(int id,String name, String email, long phone, Boolean is_admin, String password) {
		// TODO Auto-generated method stub
		try {
			Users upUser = entitymanager.find(Users.class, id);
			
			if(upUser == null) {
				return ResponseEntity.ok(ResponseCreator.response("could not find object", null, 400));
			}
			
			else {
				
				Users user = new Users();
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
				String cryptoPassword = passwordEncoder.encode(password);
				
				user.setEmail(email);
				user.setId(id);
				user.setPhone(phone);
				user.setIsAdmin(is_admin);
				user.setName(name);
				user.setPassword(cryptoPassword);
				
			
			entitymanager.merge(user);
			
			return ResponseEntity.ok(ResponseCreator.response("updated one user data successfully", null, 200));
			}
		}catch(Exception e) {
			System.out.println("Exception occures");
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot update data",null,500));
		}
	}
	
	public ResponseEntity<String> deleteUserInfo(int id) {
		// TODO Auto-generated method stub
		try {
			Users delUser = entitymanager.find(Users.class, id);
			
			if(delUser == null) {
				return ResponseEntity.ok(ResponseCreator.response("could not find object", null, 400));
			}
			
			else {
			
			entitymanager.remove(delUser);
			
			return ResponseEntity.ok(ResponseCreator.response("deleted one user data successfully", null, 200));
			}
		}catch(Exception e) {
			System.out.println("Exception occures");
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot delete data",null,500));
		}
	}



	public boolean existsByUsername(String string) {
		// TODO Auto-generated method stub
		return false;
	}



	public boolean existsByEmail(String string) {
		// TODO Auto-generated method stub
		return false;
	}



//	public ResponseEntity<String> sendEmail(String email) {
//		// TODO Auto-generated method stub
//		
//		try {
//		int otp = (int)(Math.random() * ((9999 - 1000) + 1)) + 1000;
//		
//		SimpleMailMessage message = new SimpleMailMessage(); 
//        message.setTo(email); 
//        message.setSubject("OTP from ashfi's java app"); 
//        message.setText("your Otp is: "+otp);
//        emailSender.send(message);
//		
//        return ResponseEntity.ok(ResponseCreator.response("email sent successfully", null, 200));
//		}catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
//					.response("Server error ! cannot send email",null,500));
//		}
//	}
	
//	public ResponseEntity<String> login(String name) {
//		// TODO Auto-generated method stub
//		try {
//			
//			
//			Query getNameQuery = entitymanager.createNativeQuery("Select * from users where name=:name").setParameter("name", name);
//			((org.hibernate.query.Query<Object>)getNameQuery).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//			List<Users> s =  ((org.hibernate.query.Query)getNameQuery).list();
//			
//			System.out.println(s.get(0).getPassword());
////			System.out.println(s);
//			return ResponseEntity.ok(ResponseCreator.responseUsers("user logged in successfully", s, 200));
//			
//			
//			
//		}catch(Exception e) {
//			System.out.println("Exception occures");
//			e.printStackTrace();
//			
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
//					.response("Server error ! cannot find user data",null,500));
//		}
//	}
	
	
	

//	public ResponseEntity<String> getAllUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public ResponseEntity<String> getAllUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
