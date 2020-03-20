package com.example.blog.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CommentRepo;
import com.example.blog.repository.UserRepository;
import com.example.blog.util.Blogs;
import com.example.blog.util.Busers;
import com.example.blog.util.Comments;
import com.example.blog.util.ResponseCreator;

@Service
public class BlogService {
	
	@Autowired
	EntityManager entitymanager;
	
	@Autowired
	BlogRepository blogRepo;
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	CommentRepo commRepo;
	
	

	public ResponseEntity<String> getAllBlogs() {
	
		try {
			List<Blogs> s= blogRepo.findAll();
			return ResponseEntity.ok(ResponseCreator.responseBlogs("Blogs retrieved successfully", s, 200));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("Server error ! cannot retrieve blogs details",null,500));
			
		}
	}

	public ResponseEntity<Object> getblogById(int blogId) {
		// TODO Auto-generated method stub
		try {
			
			Blogs b = entitymanager.find(Blogs.class, blogId);
			
			if( b == null) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseSingleBlog("error ! cannot find blog",null,500));
				
			}else {
				
				
				return ResponseEntity.ok(ResponseCreator.responseSingleBlog("Blog retrieved successfully", b, 200));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("Server error ! cannot find blog by id",null,500));
		}
	}

	public ResponseEntity<String> deleteBlog(int blogId) {
		
		try {

		Blogs b = new Blogs();
		
		Optional<Blogs> bId = blogRepo.findById(blogId);
		
		
		if(bId == null) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("error ! cannot find Blog",null,500));
			
		}
		else {
			b.setBlogId(blogId);
			blogRepo.delete(b);
			return ResponseEntity.ok(ResponseCreator.responseBlogs("Blog deleted successfully", null, 200));

		}
		
		}catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("Server error ! cannot delete Blog",null,500));
		}
		
		
		
	}

	public ResponseEntity<String> createBlogById(int userId, Blogs blog) {
		
		
try {
			
			Blogs b = new Blogs();
			
			
			Busers u = entitymanager.find(Busers.class, userId);
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
//			Optional<Busers> uId = userRepo.findById(userId);
			System.out.println("response for userid is: "+u);
			
			
			if(u == null) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogs("error ! cannot create Blog",null,500));
				
			}
			
			else {	
		
			b.setUsers(u);
			b.setBlogDescription(blog.getBlogDescription());
			b.setBlogDislikes(blog.getBlogDislikes());
			b.setBloglikes(blog.getBloglikes());
			b.setBlogTitle(blog.getBlogTitle());
			b.setDateTime(timestamp);
			
			System.out.println("blogs:::::"+b.getBlogTitle());
//			b.setUsers(u);
			blogRepo.save(b);
			
		return ResponseEntity.ok(ResponseCreator.responseBlogs("Blog created successfully", null, 200));
		
		}
}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("Server error ! cannot create Blog",null,500));
			
		}
		
		
	}
	
	
	
//				??????????????? COMMENT  ???????????????????

	public ResponseEntity<String> addComment(Comments comment, Blogs blog) {
		
		try{
		
			Comments c = new Comments();
			
			c.setComment(comment.getComment());
			c.setBlog_comments(blog);
			
			commRepo.save(comment);
			
		return ResponseEntity.ok(ResponseCreator.responseBlogs("comments created successfully", null, 200));
		
		
		}catch(Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseBlogs("Server error ! cannot create comment",null,500));
			
		}
		
		
	}

	public ResponseEntity<Object> getAllComments() {
		// TODO Auto-generated method stub
		try {
			List<Comments> s= commRepo.findAll();
			return ResponseEntity.ok(ResponseCreator.responseComments("Blogs retrieved successfully", s, 200));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseComments("Server error ! cannot retrieve blogs details",null,500));
			
		}
	}

	public ResponseEntity<String> deleteComment(int commId) {
		
		try {

			Comments c = entitymanager.find(Comments.class, commId);
			Comments comm = new Comments();
			
			
			if(c == null) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogs("error ! cannot find Blog",null,500));
				
			}
			else {
				
				comm.setCommentId(commId);
				commRepo.delete(comm);
				return ResponseEntity.ok(ResponseCreator.responseBlogs("Blog deleted successfully", null, 200));

			}
			
			}catch(Exception e) {
				e.printStackTrace();
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
						.responseBlogs("Server error ! cannot delete Blog",null,500));
			}
		
	}

	

}
