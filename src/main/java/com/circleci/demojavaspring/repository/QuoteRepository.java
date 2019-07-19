package com.circleci.demojavaspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.circleci.demojavaspring.model.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Long> {

    @Override
    List<Quote> findAll();

}