package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepo extends JpaRepository<Article,Integer> {

     Optional<Article> findArticleByCodeArticle(String codeArticle);
}
