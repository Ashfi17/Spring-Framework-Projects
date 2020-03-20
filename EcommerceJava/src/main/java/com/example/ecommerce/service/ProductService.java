package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerce.repository.ProductsRepo;
import com.example.ecommerce.util.Products;
import com.example.ecommerce.util.ResponseCreator;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
	
	@Autowired
	ProductsRepo proRepo;

	public ResponseEntity<String> getAllProducts() {
		// TODO Auto-generated method stub
		try {
		List<Products> s = proRepo.findAll();
		
		return ResponseEntity.ok(ResponseCreator.responseProducts("retrieved product details successfully !",s,200));
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.response("Server error ! cannot retrieve product details",null,500));
		}
	}

	public ResponseEntity<String> newProduct(String product_name, String category, int price, String description,
			int quantity) {
		// TODO Auto-generated method stub
		
		try {
		Products product = new Products();
		product.setProduct_name(product_name);
		product.setCategory(category);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		
		proRepo.save(product);
		
		return ResponseEntity.ok(ResponseCreator.response("Added one product to list !", null, 200));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator.
					response("Server error! cannot add details of product", null, 500));
		}
	}

	public ResponseEntity<String> deleteProducts(int product_id) {
		// TODO Auto-generated method stub
		
		try {
		Products productID = new Products();
		
		
		Optional<Products> pId = proRepo.findById(product_id);
		
		if(pId == null) {
			
			return ResponseEntity.ok(ResponseCreator.response("cannot find product !", null, 200));
			
		}
		else {

		productID.setProduct_id(product_id);
		proRepo.delete(productID);
		
		return ResponseEntity.ok(ResponseCreator.response("deleted one product to successfully !", null, 200));
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseCreator
					.responseProducts("Server error! cannot delete product", null, 500));
		}
	}

	

}
