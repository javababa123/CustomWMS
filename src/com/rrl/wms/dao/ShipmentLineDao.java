package com.rrl.wms.dao;

import com.rrl.wms.entity.ShipmentLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ShipmentLineDao extends JpaRepository<ShipmentLine, Serializable>{
}
