package com.example.learningspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learningspring.entity.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}


