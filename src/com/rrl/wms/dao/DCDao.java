package com.rrl.wms.dao;

import com.rrl.wms.entity.DC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DCDao extends JpaRepository<DC, Serializable> {

    DC findByDcCode(String dcCode);
}
