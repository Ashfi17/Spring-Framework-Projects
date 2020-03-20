package com.example.cricket.util;

import java.util.HashMap;
import java.util.List;

public class ResponseCreator {

	public static String response (String msg, List<Object> data, int code, HashMap<String, String> errors) {
		
		MyObject o = new MyObject();
		o.setMessage(msg);
		o.setData(data);
		o.setCode(code);
		o.setErrors(errors);
		System.out.println("Obj"+ObjToJSON.Obj2JSON(o));
		return ObjToJSON.Obj2JSON(o);
		
	}

//	public static Object response(String string, HashMap<String, String> v, int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
}
