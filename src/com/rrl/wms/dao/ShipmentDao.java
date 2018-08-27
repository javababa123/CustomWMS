package com.rrl.wms.dao;

import com.rrl.wms.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ShipmentDao extends JpaRepository<Shipment, Serializable>{

    Shipment findByShipmentNo(String shipmentNo);

    List<Shipment> findByShipmentNoAndTenantId(String shipmentNo, String tenantId);
}
