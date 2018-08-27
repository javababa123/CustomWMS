package com.rrl.wms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RFS_SHIPMENT")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class Shipment extends AuditableEntity<Shipment> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String shipmentKey;

    @Column
    protected String shipmentNo;

    @Column
    protected String tenantId;

    @Column
    protected String dcCode;

    @Column
    protected String shipmentType;

    @Column
    protected String scac;

    @Column
    protected String orderNo;

    @Column
    protected String awbNo;

    @Column
    protected String containerScm;

    @Column
    protected String containerType;

    @Column
    protected String manifestNo;

    @Column
    protected String status;

    @Column
    protected Float totalAmount;

    @Column
    protected String paymentType;

    @Column
    protected String ewaybillNo;

    @Column
    protected String addressLine1;

    @Column
    protected String addressLine2;

    @Column
    protected String addressLine3;

    @Column
    protected String addressLine4;

    @Column
    protected String city;

    @Column
    protected String state;

    @Column
    protected String zipCode;

    @Column
    protected String country;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="shipment")
    @JsonManagedReference
    protected List<ShipmentLine> shipmentLineList = new ArrayList<>();

    public List<ShipmentLine> getShipmentLineList() {
        return shipmentLineList;
    }

    public void setShipmentLineList(List<ShipmentLine> shipmentLineList) {
        this.shipmentLineList = shipmentLineList;
    }

    public String getShipmentKey() {
        return shipmentKey;
    }

    public void setShipmentKey(String shipmentKey) {
        this.shipmentKey = shipmentKey;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
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

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getScac() {
        return scac;
    }

    public void setScac(String scac) {
        this.scac = scac;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAwbNo() {
        return awbNo;
    }

    public void setAwbNo(String awbNo) {
        this.awbNo = awbNo;
    }

    public String getContainerScm() {
        return containerScm;
    }

    public void setContainerScm(String containerScm) {
        this.containerScm = containerScm;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getManifestNo() {
        return manifestNo;
    }

    public void setManifestNo(String manifestNo) {
        this.manifestNo = manifestNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getEwaybillNo() {
        return ewaybillNo;
    }

    public void setEwaybillNo(String ewaybillNo) {
        this.ewaybillNo = ewaybillNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "no=" + shipmentNo +
                ", tenant='" + tenantId + '\'' +
                ", orderNo='" + orderNo  + '\'' +
                '}';
    }
}
