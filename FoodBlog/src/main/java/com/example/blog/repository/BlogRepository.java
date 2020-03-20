package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.util.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs,Integer> {

}
