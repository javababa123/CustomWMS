package com.rrl.wms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RFS_ARTICLE")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class Article extends AuditableEntity<Article> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String articleKey;

    @Column(name="ARTICLE_CODE")
    protected String articleCode;

    @Column
    protected String articleType;

    @Column
    protected String tenantId;

    @Column
    protected String articleDesc;

    @Column
    protected int serialCount;

    @Column
    protected Float height;

    @Column
    protected Float length;

    @Column
    protected Float width;

    @Column
    protected String dimensionUom;

    @Column
    protected Float weight;

    @Column
    protected String weightUom;

    @Column
    protected Float volume;

    @Column
    protected String volumeUom;

    @Column
    protected String hsnsacCode;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="article")
    @JsonManagedReference
    protected List<ArticleAlias> aliasList = new ArrayList<>();

    public String getArticleKey() {
        return articleKey;
    }

    public void setArticleKey(String articleKey) {
        this.articleKey = articleKey;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public int getSerialCount() {
        return serialCount;
    }

    public void setSerialCount(int serialCount) {
        this.serialCount = serialCount;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public String getDimensionUom() {
        return dimensionUom;
    }

    public void setDimensionUom(String dimensionUom) {
        this.dimensionUom = dimensionUom;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getWeightUom() {
        return weightUom;
    }

    public void setWeightUom(String weightUom) {
        this.weightUom = weightUom;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getVolumeUom() {
        return volumeUom;
    }

    public void setVolumeUom(String volumeUom) {
        this.volumeUom = volumeUom;
    }

    public String getHsnsacCode() {
        return hsnsacCode;
    }

    public void setHsnsacCode(String hsnsacCode) {
        this.hsnsacCode = hsnsacCode;
    }

    public List<ArticleAlias> getAliasList() {
        return aliasList;
    }

    public void setAliasList(List<ArticleAlias> aliasList) {
        this.aliasList = aliasList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + articleCode +
                ", desc='" + articleDesc + '\'' +
                ", imageURL='" +  + '\'' +
                '}';
    }
}
