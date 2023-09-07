package com.example.learningspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class LikeRecord {
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@EmbeddedId
	private LikeId likeId;
  
  public LikeRecord() {
	}

public LikeRecord(long id, LikeId likeId) {
		super();
		this.id = id;
		this.likeId = likeId;
	}

public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LikeId getLikeId() {
		return likeId;
	}

	public void setLikeId(LikeId likeId) {
		this.likeId = likeId;
	}

}


