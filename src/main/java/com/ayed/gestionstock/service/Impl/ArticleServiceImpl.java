package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.ArticleRepo;
import com.ayed.gestionstock.service.ArticleService;
import com.ayed.gestionstock.validator.articleValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepo articleRepo;
    private mappersDTO mappersDTo;

    public ArticleServiceImpl(ArticleRepo articleRepo, mappersDTO mappersDTo) {
        this.articleRepo = articleRepo;
        this.mappersDTo = mappersDTo;

    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        List<String> errors = articleValidator.validate(articleDTO);
        if (!errors.isEmpty()) {
            log.error("Article n'est pas Valide {}", articleDTO);
            throw new invalidEntityException("Article n'est pas Valide ", errorCodes.ART_NOT_VALID);
        }

//        Article article =articleRepo.save(mappersDTo.articletoEntity(articleDTO));
//        ArticleDTO articleDTO1 =mappersDTo.articletoDTO(article);
//        return articleDTO1;
        return mappersDTo.articletoDTO(
                articleRepo.save(
                        mappersDTo.articletoEntity(articleDTO)));
    }

    @Override
    public ArticleDTO findById(Integer id) {
        if (id==null){
            log.error("Article id is null");
            return null;
        }
        Optional<Article> article =articleRepo.findById(id);

        return Optional.of(mappersDTo.articletoDTO(article.get())).orElseThrow( () ->
                new entityNotFoundException("Aucun article avec l'id = "+id+" dans BD",
                errorCodes.ART_NOT_FOUND));
    }

    @Override
    public ArticleDTO findByCodeArticle(String codeArticle) {
        if (codeArticle==null){
            log.error("Article id is null");
            return null;
        }
        Optional<Article> article =articleRepo.findArticleByCodeArticle(codeArticle);

        return Optional.of(mappersDTo.articletoDTO(article.get())).orElseThrow( () ->
                new entityNotFoundException("Aucun article avec le code = "+codeArticle+" dans BD",
                        errorCodes.ART_NOT_FOUND));
    }

    @Override
    public List<ArticleDTO> findAll() {
        return articleRepo
                .findAll()
                .stream()
                .map(mappersDTo::articletoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Article id is null");
        return;
        }
        articleRepo.deleteById(id);
    }
}
