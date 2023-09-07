package com.example.learningspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CommentRecord {
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="commenter_id", referencedColumnName="id")
	private User commenter;
	
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="id")
	private Post post;
	
	
	private String content;
  
	public CommentRecord() {
	}


	public CommentRecord(int id, CommentId commentId, String content) {
		super();
		this.id = id;
//		this.commentId = commentId;
		this.content = content;
	}


	public User getCommenter() {
		return commenter;
	}


	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public CommentId getCommentId() {
//		return commentId;
//	}

//	public void setCommentId(CommentId commentId) {
//		this.commentId = commentId;
//	}

  
}

