package com.example.learningspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learningspring.entity.LikeId;
import com.example.learningspring.entity.LikeRecord;
import com.example.learningspring.entity.Post;

	public interface LikeCRUDRepository extends CrudRepository<LikeRecord, LikeId>{
	  public Integer countByLikeIdPost(Post post);
	}


