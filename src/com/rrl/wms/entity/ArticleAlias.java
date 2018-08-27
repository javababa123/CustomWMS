package com.rrl.wms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "RFS_ARTICLE_ALIAS")
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class ArticleAlias extends AuditableEntity<ArticleAlias> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String aliasKey;

    @Column
    protected String aliasType;

    @Column
    protected String aliasVal;


    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "article_key", nullable=false)
    @JsonBackReference
    private Article article;


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getAliasKey() {
        return aliasKey;
    }

    public void setAliasKey(String aliasKey) {
        this.aliasKey = aliasKey;
    }

    public String getAliasType() {
        return aliasType;
    }

    public void setAliasType(String aliasType) {
        this.aliasType = aliasType;
    }

    public String getAliasVal() {
        return aliasVal;
    }

    public void setAliasVal(String aliasVal) {
        this.aliasVal = aliasVal;
    }
}