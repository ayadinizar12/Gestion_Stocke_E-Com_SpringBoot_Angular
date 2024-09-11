package com.ayed.gestionstock.controller;

import com.ayed.gestionstock.controller.api.CategoryApi;
import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.repository.CategoryRepo;
import com.ayed.gestionstock.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CategoryController implements CategoryApi {

    private CategoryService  categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {

        return categoryService.save(categoryDTO);
    }

    @Override
    public CategoryDTO findById(Integer id) {

        return categoryService.findById(id) ;
    }

    @Override
    public CategoryDTO findByCode(String codeCategory) {
        return categoryService.findByCodeCategory(codeCategory) ;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
