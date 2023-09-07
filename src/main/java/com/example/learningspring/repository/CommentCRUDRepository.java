package com.example.learningspring.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.learningspring.entity.CommentId;
import com.example.learningspring.entity.CommentRecord;
import com.example.learningspring.entity.*;


		public interface CommentCRUDRepository extends CrudRepository<CommentRecord, Integer>{
		 public Integer countByPost(Post post);
}
//
//
//



