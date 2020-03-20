package com.example.blog.util;



import java.util.List;

//import com.example.ecommerce.util.MyObject;
import com.example.blog.util.ObjToJSON;

public class ResponseCreator {

	public static String responseBlogUsers(String msg, List<Busers> user, int code) {
		// TODO Auto-generated method stub
		MyObject o = new MyObject();
		o.setMessage(msg);
		o.setUser(user);
		o.setCode(code);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
	}
	public static String responseBlogs(String msg, List<Blogs> blog, int code) {
		// TODO Auto-generated method stub
		MyObject o = new MyObject();
		o.setMessage(msg);
		o.setBlogs(blog);
		o.setCode(code);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
	}
	public static Object responseSingleBlog(String message, Blogs b, int code) {
		// TODO Auto-generated method stub
	
		MyObject o = new MyObject();
		o.setMessage(message);
		o.setBlog(b);
		o.setCode(code);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
	}
	public static Object responseComments(String message, List<Comments> c, int code) {
		// TODO Auto-generated method stub
		MyObject o = new MyObject();
		o.setMessage(message);
		o.setComment(c);
		o.setCode(code);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
	}
}

