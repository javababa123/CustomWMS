package com.rrl.wms.service.impl;

import com.rrl.wms.dao.ShipmentDao;
import com.rrl.wms.entity.Shipment;
import com.rrl.wms.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    ShipmentDao shipmentDao;

    public Shipment createShipment(Shipment shipment){
        return shipmentDao.save(shipment);
    }

    public Shipment findByShipmentNo(String shipmentNo){
        return shipmentDao.findByShipmentNo(shipmentNo);
    }

    public List<Shipment> findByShipmentNoAndTenantId(String shipmentNo, String tenantId){
        return shipmentDao.findByShipmentNoAndTenantId(shipmentNo, tenantId);
    }
}
