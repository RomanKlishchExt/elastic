package com.rk.elastic.service;

import com.rk.elastic.model.Article;
import com.rk.elastic.reposotory.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final LoadService loadService;
    private final ArticleRepository articleRepository;

    public void saveImport(MultipartFile file) {
        List<Article> articleList = loadService.load(file);
        articleRepository.saveAll(articleList);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).getContent();
    }
}
