package com.example.learningspring.controller.binding;


import lombok.Data;

@Data
public class AddPostForm {
  private String content;
  private Long userId;
 
  
  public AddPostForm() {
		super();
	}
	public AddPostForm(String content, Long userId) {
		super();
		this.content = content;
		this.userId = userId;
	}
	
	
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
}