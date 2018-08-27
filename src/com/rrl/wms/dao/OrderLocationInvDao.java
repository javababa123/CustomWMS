package com.rrl.wms.dao;

import com.rrl.wms.entity.OrderLocationInv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrderLocationInvDao extends JpaRepository<OrderLocationInv, Serializable> {


    @Query(value = "SELECT * FROM RFS_ORD_LOCATION_INV WHERE TENANT_ID=?1 AND ARTICLE_CODE = ?2 AND DC_CODE = ?3 AND LOCATION_ID LIKE CONCAT(CONCAT('%', ?4),'%') AND SHIPMENT_NO!=' ' ORDER BY CREATED_AT",nativeQuery=true)
    List<OrderLocationInv> findRecordForItemScan(String sTenant, String articleCode, String dcCode, String locationId);

    @Query(value = "SELECT * FROM RFS_ORD_LOCATION_INV WHERE TENANT_ID=?1 AND DC_CODE = ?2 AND LOCATION_ID LIKE CONCAT(CONCAT('%', ?3),'%') AND SHIPMENT_NO=?4",nativeQuery=true)
    List<OrderLocationInv> findRecordForItemScanWithShipmentNo(String sTenant, String dcCode, String locationId, String shipmentNo);

    @Query(value = "SELECT * FROM RFS_ORD_LOCATION_INV WHERE TENANT_ID=?1 AND DC_CODE = ?2 AND LOCATION_ID =?3 AND SHIPMENT_NO!=?4",nativeQuery=true)
    List<OrderLocationInv> findRecordForLocationScan(String sTenant, String dcCode, String locationId, String shipmentNo);

    @Query(value = "SELECT * FROM RFS_ORD_LOCATION_INV WHERE ARTICLE_CODE = ?1 AND TENANT_ID=?2 AND DC_CODE = ?4 AND LOCATION_ID =?3 AND STATUS=?5 AND INV_TYPE=?6 AND SHIPMENT_NO=?7",nativeQuery=true)
    OrderLocationInv findRecordWithoutOrderNo(String articleCode, String tenantId, String locationId, String dcCode, String status, String invType, String shipmentNo);

    List<OrderLocationInv> findByArticleCodeAndDcCodeAndLocationIdStartingWithOrderByCreatedAt(String articleCode, String dcCode, String locationId);

    OrderLocationInv findByArticleCodeAndTenantIdAndLocationId(String articleCode, String tenantId, String locationId);

    OrderLocationInv findByArticleCodeAndTenantIdAndLocationIdAndDcCodeAndStatusAndInvTypeAndOrderNoAndShipmentNo(String articleCode,
                                                                                                     String tenantId,
                                                                                                     String locationId,
                                                                                                     String dcCode,
                                                                                                     String status,
                                                                                                     String invType,
                                                                                                     String orderNo, String shipmentNo);
}
