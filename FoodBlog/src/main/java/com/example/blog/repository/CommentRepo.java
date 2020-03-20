package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.util.Comments;

public interface CommentRepo extends JpaRepository<Comments,Integer>{

}
