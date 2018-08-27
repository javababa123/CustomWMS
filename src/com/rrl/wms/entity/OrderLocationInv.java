package com.rrl.wms.entity;

import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table(name = "RFS_ORD_LOCATION_INV")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class OrderLocationInv extends AuditableEntity<OrderLocationInv> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String ordLocationInvKey;

    @Column
    public String articleCode;

    @Column
    public String tenantId;

    @Column
    public String dcCode;

    @Column
    public String locationId;

    @Column
    public String status;

    @Column
    public String invType;

    @Column
    public Float quantity;

    @Column
    public String shipmentNo;

    @Column
    public String orderNo;

    @Transient
    public String operation;

    public void setOperation(String operation) { this.operation = operation; }

    public String getOperation() { return operation; }

    public String getOrdLocationInvKey() {
        return ordLocationInvKey;
    }

    public void setOrdLocationInvKey(String ordLocationInvKey) {
        this.ordLocationInvKey = ordLocationInvKey;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode=articleCode;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDcCode() {
        return dcCode;
    }

    public void setDcCode(String dcCode) {
        this.dcCode = dcCode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "OrderLocationInv{" +
                "OrderNo=" + orderNo +
                ", Location='" + locationId + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
