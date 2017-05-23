package com.circleci.demojavaspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.circleci.demojavaspring.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    
}