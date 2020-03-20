package com.example.blog.util;



import java.util.List;
public class MyObject {

	
	private String message;
	private List<Object> data;
	private List<Busers> user;
	private List<Blogs> blogs;
	private List<Comments> comment;
	private Busers u;
	private Blogs blog;
	public Blogs getBlog() {
		return blog;
	}
	public void setBlog(Blogs blog) {
		this.blog = blog;
	}
	private int code;

	
	
	
	
	public List<Comments> getComment() {
		return comment;
	}
	public void setComment(List<Comments> comment) {
		this.comment = comment;
	}
	public Busers getU() {
		return u;
	}
	public void setU(Busers u) {
		this.u = u;
	}
	public List<Blogs> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blogs> blogs) {
		this.blogs = blogs;
	}
	public String getMessage() {
		return message;
	}
	public List<Busers> getUser() {
		return user;
	}
	public void setUser(List<Busers> user) {
		this.user = user;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getData() {
		return data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}

