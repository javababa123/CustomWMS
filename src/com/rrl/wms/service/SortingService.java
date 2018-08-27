package com.rrl.wms.service;

import com.rrl.wms.entity.OrderLocationInv;
import com.rrl.wms.entity.Shipment;

import java.util.List;

public interface SortingService {

    public List<OrderLocationInv>  scanItem(String sTenant, String sItem, String sDC) throws Exception;

    public List<OrderLocationInv>  scanLocation(String sTenant, String sItem, String sDC, String sShipment, String sLocation) throws Exception;

}
