package com.rrl.wms.service;

import com.rrl.wms.entity.Shipment;

import java.util.List;

public interface ShipmentService {

    Shipment createShipment(Shipment shipment);

    Shipment findByShipmentNo(String shipmentNo);

    List<Shipment> findByShipmentNoAndTenantId(String shipmentNo, String tenantId);
}
