package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.util.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Integer>{

}
