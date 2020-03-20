package com.example.blog.util;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Blogs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blog_id")
	private int blogId;
	@Column(name = "blog_title")
	private String blogTitle;
	@Column(name = "blog_likes")
	private int bloglikes;
	@Column(name = "blog_dislikes")
	private int blogDislikes;
	@Column(name = "blog_description")
	private String blogDescription;
	@Column(name = "blog_dateTime")
	private Timestamp dateTime;
	
	@JsonIgnore
	@OneToMany(mappedBy = "blog_comments")
	private List<Comments> comments = new ArrayList<>();	
	
//	@JsonIgnore
	@ManyToOne
	private Busers users;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy ="blogTag")
	private List<Tags> tags = new ArrayList<>();
	
	
	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	
	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	
	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public int getBloglikes() {
		return bloglikes;
	}

	public void setBloglikes(int bloglikes) {
		this.bloglikes = bloglikes;
	}

	public int getBlogDislikes() {
		return blogDislikes;
	}

	public void setBlogDislikes(int blogDislikes) {
		this.blogDislikes = blogDislikes;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public Busers getUsers() {
		return users;
	}

	public void setUsers(Busers users) {
		this.users = users;
	}
	
	

}
