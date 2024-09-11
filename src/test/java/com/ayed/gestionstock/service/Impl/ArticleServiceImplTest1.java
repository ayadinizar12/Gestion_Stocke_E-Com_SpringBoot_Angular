package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.service.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceImplTest1 {

    private ArticleService articleService;

    public ArticleServiceImplTest1(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Test
    public void saveArticle() {
        ArticleDTO buildArticleDTO =ArticleDTO.builder()
                .codeArticle("teste")
                .designation("dsg teste")
                .idEntreprise(1)
                .build();
        ArticleDTO articleDTOtest = articleService.save(buildArticleDTO);
        assertNotNull(articleDTOtest);
        assertNotNull(articleDTOtest.getId());
        assertEquals(buildArticleDTO.getCodeArticle(),articleDTOtest.getCodeArticle());
        assertEquals(buildArticleDTO.getDesignation(),articleDTOtest.getDesignation());
        assertEquals(buildArticleDTO.getIdEntreprise(),articleDTOtest.getIdEntreprise());

    }

    @Test
    void findById() {
    }

    @Test
    void findByCodeArticle() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}