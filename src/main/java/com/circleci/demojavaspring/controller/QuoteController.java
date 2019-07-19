package com.circleci.demojavaspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.circleci.demojavaspring.model.Quote;
import com.circleci.demojavaspring.repository.QuoteRepository;

@Controller
@RequestMapping(path="/quote")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewQuote(@RequestParam String quote) {
        Quote q = new Quote();
        q.setQuote("New Quote");
        quoteRepository.save(q);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody List<Quote> list() {
        return quoteRepository.findAll();
    }
}