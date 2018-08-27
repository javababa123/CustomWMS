package com.rrl.wms.service;

import com.rrl.wms.entity.Article;
import com.rrl.wms.util.exception.RWMSException;

import java.util.List;

public interface ArticleService {

    public Article createArticle(Article article);

    public List<Article> getArticleList();

    public Article updateArticle(Article article) throws RWMSException;;
}
