package com.example.ecommerce.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.service.UserService;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {


	@Autowired
	UserService us;
	
	
	@GetMapping("/allUsers")
	public ResponseEntity<String> getAllUser(){
		return us.getAllUser();
		
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody HashMap<String,Object> user){
		
		String user_phone = (String) user.get("phone");
		long long_phone = Long.parseLong(user_phone);
	
		return us.createUser((String)user.get("name"),
				(String)user.get("email"),long_phone,(Boolean)user.get("is_admin"),(String)user.get("password"));	
		}
	
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> createUser(@PathVariable int id, @RequestBody HashMap<String,Object> user){
		
		String user_phone = (String) user.get("phone");
		long long_phone = Long.parseLong(user_phone);
	
		return us.updateUser(id,(String)user.get("name"),
				(String)user.get("email"),long_phone,(Boolean)user.get("is_admin"),(String)user.get("password"));	
		}
	
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUserInfo(@PathVariable int id){
		
		return us.deleteUserInfo(id);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody HashMap<String,Object> user){
		return us.login((String)user.get("name"),(String)user.get("password"));
	}
	
	
	@PostMapping("/sendMail")
	public ResponseEntity<String> sendEmail(@RequestBody HashMap<String,Object> user){
		return us.sendEmail((String)user.get("email"));
	}
	
	@PostMapping("/verifyOtp")
	public ResponseEntity<String> verifyotp(@RequestBody int otp){
		return us.verifyotp(otp);
	}
	
	@PostMapping("/newUser")
	public ResponseEntity<String> newUser(@RequestBody HashMap<String,Object> user){
		
		String user_phone = (String) user.get("phone");
		long long_phone = Long.parseLong(user_phone);
	
		return us.newUser((String)user.get("name"),
				(String)user.get("email"),long_phone,(Boolean)user.get("is_admin"),(String)user.get("password"));	
		}
	
}
