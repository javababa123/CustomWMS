package com.rrl.wms.dao;

import com.rrl.wms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ArticleAliasDao extends JpaRepository<Article, Serializable> {
}
