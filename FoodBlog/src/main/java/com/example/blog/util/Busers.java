package com.example.blog.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Busers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_password")
	private String password;
	
	@OneToMany(mappedBy = "user_comments")
	private List<Comments> comments = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "users")
	private List<Blogs> blogs = new ArrayList<>();
	
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public List<Blogs> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blogs> blogs) {
		this.blogs = blogs;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
