package com.rk.elastic.controller;

import com.rk.elastic.model.Article;
import com.rk.elastic.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article/{id}")
    private ResponseEntity<Article> findById(@PathVariable Long id) {
        return ResponseEntity.of(articleService.findById(id));
    }

    @GetMapping("/article")
    private List<Article> findAll(@PageableDefault(size = 50) Pageable pageable) {
        return articleService.findAll(pageable);
    }
}
