package com.example.ecommerce.util;

import java.util.List;

import com.example.ecommerce.util.MyObject;
import com.example.ecommerce.util.ObjToJSON;

public class ResponseCreator {

	public static String response(String msg, List<Object> data, int code) {
		// TODO Auto-generated method stub
		MyObject o = new MyObject();
		o.setMessage(msg);
		o.setData(data);
		o.setCode(code);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
	}
	public static String responseProducts(String msg, List<Products> product, int code) {
		// TODO Auto-generated method stub
		MyObject o = new MyObject();
		o.setMessage(msg);
		o.setProduct(product);
		o.setCode(code);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
	}
}
