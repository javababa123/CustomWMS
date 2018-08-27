package com.rrl.wms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rrl.wms.dao.ArticleDao;
import com.rrl.wms.dao.LocationDao;
import com.rrl.wms.entity.Article;
import com.rrl.wms.entity.ArticleAlias;
import com.rrl.wms.entity.Location;
import com.rrl.wms.service.ArticleService;
import com.rrl.wms.util.exception.RWMSException;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    LocationDao locationDao;

    public Article createArticle(Article article){
        return articleDao.save(article);
    }

    public List<Article> getArticleList(){
        return articleDao.findAll();
    }

    @Override
    public Article updateArticle(Article article) throws RWMSException{

        List<Article> articleList = articleDao.findByArticleCodeAndTenantId(article.getArticleCode(),article.getTenantId());
        Article existingArticle = null;
        if (articleList.size()>0) {
            existingArticle = articleList.get(0);
        } else {
            throw new RWMSException("Article Doesn't exist");
        }

        if (null != existingArticle) {
            //Setting new values in existing article Object
            existingArticle.setArticleDesc(article.getArticleDesc());
            existingArticle.setSerialCount(article.getSerialCount());
            existingArticle.setHeight(article.getHeight());
            existingArticle.setLength(article.getLength());
            existingArticle.setWidth(article.getWidth());
            existingArticle.setDimensionUom(article.getDimensionUom());
            existingArticle.setWeight(article.getWeight());
            existingArticle.setWeightUom(article.getWeightUom());
            existingArticle.setVolume(article.getVolume());
            existingArticle.setVolumeUom(article.getVolumeUom());
            existingArticle.setHsnsacCode(article.getHsnsacCode());

            List<ArticleAlias> newAliasList = article.getAliasList();
            //if new alias list is empty then remove entries from DB
            if(newAliasList.isEmpty()) {
                existingArticle.getAliasList().clear();
            }
            //otherwise update or add alias
            for (ArticleAlias articleAlias: newAliasList) {
                articleAlias.setArticle(existingArticle);
                Optional<ArticleAlias> articleAliasOpt = existingArticle.getAliasList().stream().filter(existingArticleAlias -> articleAlias.getAliasType().equals(existingArticleAlias.getAliasType())).findAny();

                if (articleAliasOpt.isPresent()) {
                    articleAliasOpt.get().setAliasVal(articleAlias.getAliasVal());
                } else {
                    existingArticle.getAliasList().add(articleAlias);
                }

            }
            return articleDao.save(existingArticle);
        }
        return articleDao.save(article);
    }
}
