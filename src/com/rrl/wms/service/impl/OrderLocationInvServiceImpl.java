package com.rrl.wms.service.impl;

import com.rrl.wms.dao.*;
import com.rrl.wms.entity.*;
import com.rrl.wms.service.OrderLocationInvService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderLocationInvServiceImpl implements OrderLocationInvService{

    @Autowired
    OrderLocationInvDao orderLocationInvDao;
    @Autowired
    TenantDao tenantDao;
    @Autowired
    LocationDao locationDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    DCDao dcDao;


    @Override
    public List<OrderLocationInv> getOrderLocationInvList(){
        return orderLocationInvDao.findAll();
    }


    @Override
    public OrderLocationInv recordInv(OrderLocationInv orderLocationInv){
        return orderLocationInvDao.save(orderLocationInv);
    }

    @Override
    public OrderLocationInv findRecord(OrderLocationInv orderLocationInv){
        return orderLocationInvDao.findByArticleCodeAndTenantIdAndLocationIdAndDcCodeAndStatusAndInvTypeAndOrderNoAndShipmentNo(
                orderLocationInv.getArticleCode(),orderLocationInv.getTenantId(),orderLocationInv.getLocationId(),
                orderLocationInv.getDcCode(),orderLocationInv.getStatus(),orderLocationInv.getInvType(),null,null);
    }

    public OrderLocationInv findRecordWithOrderNo(OrderLocationInv orderLocationInv){
        return orderLocationInvDao.findByArticleCodeAndTenantIdAndLocationIdAndDcCodeAndStatusAndInvTypeAndOrderNoAndShipmentNo(
                orderLocationInv.getArticleCode(),orderLocationInv.getTenantId(),orderLocationInv.getLocationId(),
                orderLocationInv.getDcCode(),orderLocationInv.getStatus(),orderLocationInv.getInvType(),orderLocationInv.getOrderNo(),null);
    }

    public OrderLocationInv findRecordWithShipmentNo(OrderLocationInv orderLocationInv){
        return orderLocationInvDao.findByArticleCodeAndTenantIdAndLocationIdAndDcCodeAndStatusAndInvTypeAndOrderNoAndShipmentNo(
                orderLocationInv.getArticleCode(),orderLocationInv.getTenantId(),orderLocationInv.getLocationId(),
                orderLocationInv.getDcCode(),orderLocationInv.getStatus(),orderLocationInv.getInvType(),null,orderLocationInv.getShipmentNo());
    }

    public OrderLocationInv findRecordWithOrderNoAndShipmentNo(OrderLocationInv orderLocationInv){
        return orderLocationInvDao.findByArticleCodeAndTenantIdAndLocationIdAndDcCodeAndStatusAndInvTypeAndOrderNoAndShipmentNo(
                orderLocationInv.getArticleCode(),orderLocationInv.getTenantId(),orderLocationInv.getLocationId(),
                orderLocationInv.getDcCode(),orderLocationInv.getStatus(),orderLocationInv.getInvType(),orderLocationInv.getOrderNo(),orderLocationInv.getShipmentNo());
    }

    private boolean isNull(String sInput) {
        if(sInput != null && !sInput.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public OrderLocationInv adjustInventory(OrderLocationInv orderLocationInv) throws Exception{
        validateInput(orderLocationInv);
        OrderLocationInv currentInventory=null;
        if(!isNull(orderLocationInv.getOrderNo()) && !isNull(orderLocationInv.getShipmentNo())){
            currentInventory = findRecordWithOrderNoAndShipmentNo(orderLocationInv);
        }
        if(!isNull(orderLocationInv.getOrderNo()) && isNull(orderLocationInv.getShipmentNo())){
            currentInventory = findRecordWithOrderNo(orderLocationInv);
        }
        if(isNull(orderLocationInv.getOrderNo()) && !isNull(orderLocationInv.getShipmentNo())){
            currentInventory = findRecordWithShipmentNo(orderLocationInv);
        }

        String sOperation = orderLocationInv.getOperation();

        if("+".equals(sOperation)){
            if(currentInventory==null){
                //if currentInventory is null we need to create the record
                recordInv(orderLocationInv);
            }
            else{
                //increment the inventory
                Float fCurrentQuantity = currentInventory.getQuantity();
                Float fNewQuantity = orderLocationInv.getQuantity();
                if(fNewQuantity>0){
                    System.out.println(fNewQuantity);
                    fNewQuantity = fNewQuantity + fCurrentQuantity;
                    currentInventory.setQuantity(fNewQuantity);
                    orderLocationInvDao.save(currentInventory);
                }
            }

        }
        if("-".equals(sOperation)){
            if(currentInventory==null){
                //no inventory found, throw error
                throw new Exception("Inventory not found");
            }
            else{
                //decrement the inventory
                Float fCurrentQuantity = currentInventory.getQuantity();
                Float fNewQuantity = orderLocationInv.getQuantity();
                fNewQuantity = fCurrentQuantity-fNewQuantity;
                if(fNewQuantity>0){
                    currentInventory.setQuantity(fNewQuantity);
                    orderLocationInvDao.save(currentInventory);
                }
                else {
                    //delete the record
                    orderLocationInvDao.delete(currentInventory.getOrdLocationInvKey());
                }
            }

        }
        if(currentInventory==null)
            currentInventory = orderLocationInv;
        return currentInventory;
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Override
    public void validateInput(OrderLocationInv orderLocationInv) throws Exception{
        String sTenantID = orderLocationInv.getTenantId();
        String sLocationID = orderLocationInv.getLocationId();
        String sArticleCode = orderLocationInv.getArticleCode();
        String sDCCode = orderLocationInv.getDcCode();
        String sStatus = orderLocationInv.getStatus();
        String sInvType = orderLocationInv.getInvType();
        String sOperation = orderLocationInv.getOperation();

        if(isNull(sOperation)){
            throw new Exception("Operation cannot be null");
        }
        else {
            if(!("+".equals(sOperation) || "-".equals(sOperation))){
                throw new Exception("Invalid Operation - "+sOperation);
            }
        }

        if(sStatus != null && !sStatus.isEmpty()){

            if(!("GOOD".equals(sStatus) || "BAD".equals(sStatus)))
                throw new Exception("Invalid Status - "+sStatus);
        }
        else {
            throw new Exception("Status cannot be null");
        }

        if(sInvType != null && !sInvType.isEmpty()){

            if(!("ORD_INV".equals(sInvType) || "SHP_INV".equals(sInvType)))
                throw new Exception("Invalid InvType - "+sInvType);
        }
        else {
            throw new Exception("InvType cannot be null");
        }


        if(sTenantID != null && !sTenantID.isEmpty()){

            Tenant tenant = tenantDao.findByTenantId(sTenantID);
            if(tenant==null){
                throw new Exception("Invalid TenantID - "+sTenantID);
            }
        }
        else {
            throw new Exception("TenantID cannot be null");
        }

        if(sLocationID != null && !sLocationID.isEmpty()){

            Location location = locationDao.findByLocationId(sLocationID);
            if(location==null){
                throw new Exception("Invalid LocationID - "+sLocationID);
            }
        }
        else {
            throw new Exception("LocationID cannot be null");
        }

        if(sArticleCode != null && !sArticleCode.isEmpty()){

            if(articleDao.findByArticleCodeAndTenantId(sArticleCode,sTenantID).size()==0)
            {
                throw new Exception("Invalid ArticleCode - "+sArticleCode);
            }
        }
        else {
            throw new Exception("ArticleCode cannot be null");
        }

        if(sDCCode != null && !sDCCode.isEmpty()){

            DC dc = dcDao.findByDcCode(sDCCode);
            if(dc==null){
                throw new Exception("Invalid DCCode - "+sDCCode);
            }
        }
        else {
            throw new Exception("DCCode cannot be null");
        }
    }
}
