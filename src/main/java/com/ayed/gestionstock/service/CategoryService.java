package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO findById(Integer id);
    CategoryDTO findByCodeCategory(String codeCategory);
    List<CategoryDTO> findAll();
    void delete(Integer id);
}
