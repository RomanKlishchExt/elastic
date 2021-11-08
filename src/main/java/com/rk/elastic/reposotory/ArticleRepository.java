package com.rk.elastic.reposotory;

import com.rk.elastic.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    Optional<Article> findById(Long id);
}
