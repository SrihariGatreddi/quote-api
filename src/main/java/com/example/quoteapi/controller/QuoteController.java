package com.example.quoteapi.controller;

import com.example.quoteapi.model.Quote;
import com.example.quoteapi.service.QuoteService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
@Tag(name = "Quotes API", description = "Get quotes and quote by ID")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}")
    public Quote getQuote(@PathVariable Long id) {
        return quoteService.getQuote(id);
    }
}
