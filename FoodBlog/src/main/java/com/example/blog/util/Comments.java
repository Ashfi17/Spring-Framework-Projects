package com.example.blog.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	private int commentId;
	
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne
	private Blogs blog_comments;
	@ManyToOne
	private Busers user_comments;
	
	public Blogs getBlog_comments() {
		return blog_comments;
	}

	public void setBlog_comments(Blogs blog_comments) {
		this.blog_comments = blog_comments;
	}

	public Busers getUser_comments() {
		return user_comments;
	}

	public void setUser_comments(Busers user_comments) {
		this.user_comments = user_comments;
	}

	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	

}
