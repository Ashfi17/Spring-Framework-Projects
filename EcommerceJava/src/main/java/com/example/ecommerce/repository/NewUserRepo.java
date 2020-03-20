package com.example.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.util.Products;
import com.example.ecommerce.util.Users;

@Repository
public interface NewUserRepo extends JpaRepository<Users,Integer> {

	
//	List<Users> findByName(String name);
	
	@Query(value = "SELECT * from users WHERE name = :name", nativeQuery = true)
	Users findByName(String name);
	
	@Query(value = "SELECT * from users WHERE email=:email",nativeQuery = true)
	Users findByEmail(String email);
	
	Boolean existsByUsername(String name);

    Boolean existsByEmail(String email);
	
}
