package com.rrl.wms.service.impl;

import com.rrl.wms.dao.ArticleDao;
import com.rrl.wms.dao.LocationDao;
import com.rrl.wms.dao.OrderLocationInvDao;
import com.rrl.wms.entity.Location;
import com.rrl.wms.entity.OrderLocationInv;
import com.rrl.wms.entity.Shipment;
import com.rrl.wms.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortingServiceImpl implements SortingService {

    @Autowired
    OrderLocationInvServiceImpl orderLocationInvService;

    @Autowired
    OrderLocationInvDao orderLocationInvDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    LocationDao locationDao;

    @Override
    public List<OrderLocationInv>  scanItem(String sTenant, String sItem, String sDC) throws Exception{

        if(sItem != null && !sItem.isEmpty()){

            if(articleDao.findByArticleCodeAndTenantId(sItem,sTenant).size()==0)
            {
                throw new Exception("Invalid Item - "+sItem);
            }
        }
        else {
            throw new Exception("Item cannot be blank");
        }


        List<OrderLocationInv>  orderLocationInvList = orderLocationInvDao.findRecordForItemScan(sTenant, sItem, sDC,"STG");

        if(orderLocationInvList.size()>0){
            for(OrderLocationInv orderLocationInv : orderLocationInvList){
                //iterate thru all the records and check if there any shipment which is in being sorted by query inventory table for that shipment and location starts with pigeon
                String sShipmentNo = orderLocationInv.getShipmentNo();
                List<OrderLocationInv>  shipmentLocationInvList = orderLocationInvDao.findRecordForItemScanWithShipmentNo(sTenant, sDC,"PG", sShipmentNo);
                if(shipmentLocationInvList.size()>0){
                    return orderLocationInvDao.findRecordForItemScanWithShipmentNo(sTenant, sDC,"", sShipmentNo);
                }
            }
            String sShipmentNo = orderLocationInvList.get(0).getShipmentNo();
            return orderLocationInvDao.findRecordForItemScanWithShipmentNo(sTenant, sDC,"", sShipmentNo);
        }
        else {
            Exception exp = new Exception("No Shipment found against this Item");
            throw exp;
        }
    }

    @Override
    public List<OrderLocationInv>  scanLocation(String sTenant, String sItem, String sDC, String sShipment, String sLocation) throws Exception{

        if(sLocation != null && !sLocation.isEmpty()){

            Location location = locationDao.findByLocationId(sLocation);
            if(location==null){
                throw new Exception("Invalid Location - "+sLocation);
            }
        }
        else {
            throw new Exception("Location cannot be blank");
        }

        List<OrderLocationInv>  orderLocationInvList = orderLocationInvDao.findRecordForLocationScan(sTenant, sDC, sLocation, sShipment);
        if(orderLocationInvList.size()==0){

            OrderLocationInv orderLocationInv = orderLocationInvDao.findRecordWithoutOrderNo(
                    sItem, sTenant, sDC+"-STG", sDC, "GOOD","SHP_INV",sShipment);

            System.out.println("============="+orderLocationInv.getShipmentNo());
            orderLocationInv.setOperation("-");

            orderLocationInvService.adjustInventory(orderLocationInv);

            orderLocationInv.setOperation("+");
            orderLocationInv.setLocationId(sLocation);
            orderLocationInvService.adjustInventory(orderLocationInv);

            return orderLocationInvDao.findRecordForItemScanWithShipmentNo(sTenant, sDC,"", sShipment);
        }
        else {
            Exception exp = new Exception("Scanned location not empty");
            throw exp;
        }
    }
}
