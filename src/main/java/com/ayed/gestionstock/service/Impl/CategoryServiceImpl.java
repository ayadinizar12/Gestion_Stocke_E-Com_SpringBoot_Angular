package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.Category;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.ArticleRepo;
import com.ayed.gestionstock.repository.CategoryRepo;
import com.ayed.gestionstock.service.ArticleService;
import com.ayed.gestionstock.service.CategoryService;
import com.ayed.gestionstock.validator.articleValidator;
import com.ayed.gestionstock.validator.categoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private mappersDTO mappersDTo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, mappersDTO mappersDTo) {
        this.categoryRepo = categoryRepo;
        this.mappersDTo = mappersDTo;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        List<String> errors = categoryValidator.validate(categoryDTO);
        if(!errors.isEmpty()) {
            log.error("category n'est pas Valide {}", categoryDTO);
            throw new invalidEntityException("Category n'est pas Valide ", errorCodes.CTG_NOT_VALID,errors);
        }

        return mappersDTo.categorytoDTO(categoryRepo.save(
                mappersDTo.categorytoEntity(categoryDTO)
        ));
    }

    @Override
    public CategoryDTO findById(Integer id) {
        if (id==null){
            log.error("Category id is null");
            return null;
        }
        Optional<Category> category =categoryRepo.findById(id);
        return Optional.of(mappersDTo
                .categorytoDTO(category.get()))
                .orElseThrow(()->
                new entityNotFoundException("Aucun category avec l'id = "+id+" dans BD",
                        errorCodes.CTG_NOT_FOUND));
    }

    @Override
    public CategoryDTO findByCodeCategory(String codeCategory) {
        if (codeCategory==null){
            log.error("Category code is null");
            return null;
        }
        Optional<Category> category =categoryRepo.findArticleByCodeCategory(codeCategory);

        return Optional.of(mappersDTo.categorytoDTO(category.get())).orElseThrow(()->
                new entityNotFoundException("Aucun category avec l'id = "+codeCategory+" dans BD",
                        errorCodes.CTG_NOT_FOUND));
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepo.findAll().stream()
                .map(mappersDTo::categorytoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Article id is null");
            return;
        }
        categoryRepo.deleteById(id);
    }

}
