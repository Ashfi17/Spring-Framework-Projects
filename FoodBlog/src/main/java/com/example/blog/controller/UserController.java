package com.example.blog.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.service.UserService;
import com.example.blog.util.Busers;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blogs/users")
public class UserController {
	
	@Autowired
	UserService userServ;
	
	@GetMapping("/allUsers")
	public ResponseEntity<String> getAlluser(){
		return userServ.getAllUser();
	}
	
	@PostMapping("/newUser")
	public ResponseEntity<String> newUser(@RequestBody Busers user){
		

		return userServ.newUser(user);	
		}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		
		return userServ.deleteUser(userId);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody HashMap<String,Object> user){
		return userServ.login((String)user.get("email"),(String)user.get("password"));
	}
	
	
	@PostMapping("createNewUser/{blogId}")
	public ResponseEntity<String> createUserWithBlog(int blogId,@RequestBody Busers user){
		

		return userServ.createUserWithBlog(blogId,user);	
		}
	
//	@GetMapping("/getById/{userid}")
//	public ResponseEntity<Object> getById(@PathVariable int userId){
//		return userServ.getById(userId);
//	}
}

















