package com.example.blog.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;

@Entity
public class Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tag_id")
	private int tagId;
	@Column(name = "tags")
	private String tags;
	
	@ManyToMany
	private List<Blogs> blogTag = new ArrayList<>();
	
	public List<Blogs> getBlogs() {
		return blogTag;
	}

	public void setBlogs(List<Blogs> blogTag) {
		this.blogTag = blogTag;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	

}
