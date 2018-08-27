package com.rrl.wms.service;

import com.rrl.wms.entity.OrderLocationInv;

import java.util.List;

public interface OrderLocationInvService {

    OrderLocationInv recordInv(OrderLocationInv orderLocationInv);

    OrderLocationInv adjustInventory(OrderLocationInv orderLocationInv) throws Exception;

    OrderLocationInv findRecord(OrderLocationInv orderLocationInv);

    void validateInput(OrderLocationInv orderLocationInv) throws Exception;

    List<OrderLocationInv> getOrderLocationInvList();

    OrderLocationInv findRecordWithOrderNo(OrderLocationInv orderLocationInv);

}
