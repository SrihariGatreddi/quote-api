package com.example.quoteapi.service;

import com.example.quoteapi.model.Quote;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuoteService {

    private final Map<Long, Quote> quoteMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        quoteMap.put(1L, new Quote(1L, "Stay hungry, stay foolish.", "Steve Jobs"));
        quoteMap.put(2L, new Quote(2L, "Be yourself; everyone else is already taken.", "Oscar Wilde"));
        quoteMap.put(3L, new Quote(3L, "Simplicity is the ultimate sophistication.", "Leonardo da Vinci"));
    }

    public List<Quote> getAllQuotes() {
        return new ArrayList<>(quoteMap.values());
    }

    public Quote getQuote(Long id) {
        return quoteMap.get(id);
    }
}
