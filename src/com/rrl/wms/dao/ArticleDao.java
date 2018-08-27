package com.rrl.wms.dao;

import com.rrl.wms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, Serializable> {

    Article findByArticleCode(String articleCode);
    List<Article> findByArticleCodeAndTenantId(String articleCode, String tenantId);

}
