package com.rrl.wms.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rrl.wms.entity.OrderLocationInv;
import com.rrl.wms.entity.Shipment;
import com.rrl.wms.entity.ShipmentLine;
import com.rrl.wms.service.OrderLocationInvService;
import com.rrl.wms.service.ShipmentService;
import com.rrl.wms.util.exception.RWMSException;

@Component
public class CreateShipmentFacade {
	
	@Autowired
    public ShipmentService shipmentService;
	
	@Autowired
	public OrderLocationInvService orderLocService;
	
	@Transactional(rollbackFor = RWMSException.class)
	public Shipment createShipmentAndUpdateLocation(Shipment shipment) throws Exception{
		
		List<Shipment> existingShipmentList = shipmentService.findByShipmentNoAndTenantId(shipment.getShipmentNo(), shipment.getTenantId());
		Shipment newShipment = null;
		String strTenantId = shipment.getTenantId();
		String strOrderNo = shipment.getOrderNo();
		String strShipmentNo = shipment.getShipmentNo();
		String strDcCode = shipment.getDcCode();
		
		
		if (existingShipmentList.size()>0) {
			throw new Exception("Shipment Already Exists");
		} else {
			List<ShipmentLine> shipmentLineList = shipment.getShipmentLineList();
			for (ShipmentLine shipmentLine: shipmentLineList) {
				String strArtCode = shipmentLine.getArticleCode();
				Float fQty = shipmentLine.getQuantity();
				
				OrderLocationInv findLocationInv = new OrderLocationInv();
				findLocationInv.setArticleCode(strArtCode);
				findLocationInv.setTenantId(strTenantId);
				findLocationInv.setLocationId("V028-STG");
				findLocationInv.setDcCode(strDcCode);
				findLocationInv.setStatus("GOOD");
				findLocationInv.setInvType("ORD_INV");
				findLocationInv.setOrderNo(strOrderNo);;
				
				OrderLocationInv existingLocationInv = orderLocService.findRecordWithOrderNo(findLocationInv);
				//findByArticleCodeAndTenantIdAndLocationIdAndDcCodeAndStatusAndInvTypeAndOrderNoAndShipmentNo
//				System.out.println("Loc List:: " + locationList);
				
				if (null != existingLocationInv) {
					
//					OrderLocationInv existingLocationInv = locationList.get(0);
//					Float currentQty = existingLocationInv.getQuantity();
					
					OrderLocationInv updatedLocation = new OrderLocationInv();
					
					updatedLocation.setArticleCode(strArtCode);
					updatedLocation.setTenantId(strTenantId);
					updatedLocation.setDcCode(shipment.getDcCode());
					updatedLocation.setLocationId("V028-STG");
					updatedLocation.setStatus("GOOD");
					updatedLocation.setInvType("SHP_INV");
					updatedLocation.setQuantity(fQty);
					updatedLocation.setShipmentNo(strShipmentNo);
					updatedLocation.setOrderNo(strOrderNo);
					updatedLocation.setOperation("+");
					System.out.println("updatedLocation" + updatedLocation);
					orderLocService.adjustInventory(updatedLocation);
					
//					updatedLocation.setLocationId("STAGING");
//					updatedLocation.setInvType("ORDER_INV");
//					updatedLocation.setOperation("-");
					
//					
					existingLocationInv.setQuantity(fQty);
					existingLocationInv.setOperation("-");
					System.out.println("existingLocationInv: " + existingLocationInv);
					orderLocService.adjustInventory(existingLocationInv);
					
					
				}
				
			}
			
			
			newShipment = shipmentService.createShipment(shipment);
		}
        return newShipment;
    }

}
