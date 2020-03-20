package com.example.blog.util;



import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjToJSON {

	
	public static String Obj2JSON(Object obj) {
		// TODO Auto-generated method stub
		ObjectMapper Obj = new ObjectMapper();
		try {
			String jsonStr = Obj.writeValueAsString(obj);
			return jsonStr;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

