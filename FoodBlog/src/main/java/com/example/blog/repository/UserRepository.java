package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.blog.util.Busers;

@Repository
public interface UserRepository  extends JpaRepository<Busers,Integer>{
	
	@Query(value = "SELECT * FROM busers WHERE user_email= :email",nativeQuery = true)
	Busers findByEmail(String email);
	
//	Optional<User> findByEmail(String email);

//    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);

}
