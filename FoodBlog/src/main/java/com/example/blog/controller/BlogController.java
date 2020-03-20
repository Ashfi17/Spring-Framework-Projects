package com.example.blog.controller;

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

import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;
import com.example.blog.util.Blogs;
import com.example.blog.util.Busers;
import com.example.blog.util.Comments;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blogs/posts")
public class BlogController {

	
	@Autowired
	BlogService blogServ;
	
	@GetMapping("/allBlogs")
	public ResponseEntity<String> getAllBlogs(){
		return blogServ.getAllBlogs();
	}
	
	
	
	
	
	@DeleteMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId){
		
		return blogServ.deleteBlog(blogId);
		
	}
	
	
	@PostMapping("/createBlog/{userId}")
	
public ResponseEntity<String> createBlogById(@PathVariable int userId ,@RequestBody Blogs blog){
		
		return blogServ.createBlogById(userId,blog);
	}
	
	@GetMapping("/getBlogsById/{blogId}")
public ResponseEntity<Object> getblogById(@PathVariable int blogId){
		
		return blogServ.getblogById(blogId);
		
	}
	
	
               ////////////////////// COMMENTS //////////////////
	
	
	@PostMapping("/addComment")
	
	public ResponseEntity<String> addComment(@RequestBody Comments comment, Blogs blog){
			
			return blogServ.addComment(comment,blog);
		}
	
	@GetMapping("/allComments")
	public ResponseEntity<Object> getAllComments(){
		return blogServ.getAllComments();
	}
	
	@DeleteMapping("/deletecomment/{commId}")
	public ResponseEntity<String> deleteComment(@PathVariable int commId){
		
		return blogServ.deleteComment(commId);
	}
	
}
