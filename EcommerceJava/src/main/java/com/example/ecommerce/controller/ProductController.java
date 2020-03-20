package com.example.ecommerce.controller;

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

import com.example.ecommerce.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService ps;
	
	@GetMapping("/allProducts")
	public ResponseEntity<String> getAllProducts(){
		return ps.getAllProducts();
		
	}
	
	@PostMapping("/newProduct")
	public ResponseEntity<String> newProduct(@RequestBody HashMap<String,Object> user){
		
		String product_price = (String) user.get("price");
		int intProd_price = Integer.parseInt(product_price);
		
		String product_qty = (String) user.get("quantity");
		int intProd_qty = Integer.parseInt(product_qty);
	
		return ps.newProduct((String)user.get("product_name"),
				(String)user.get("category"),intProd_price,(String)user.get("description"),intProd_qty);	
		}
	
	@DeleteMapping("/deleteProduct/{product_id}")
	public ResponseEntity<String> deleteProducts(@PathVariable int product_id){
		return ps.deleteProducts(product_id);
		
	}
}
