package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

    Optional<Category> findArticleByCodeCategory(String codeCategory);

}
