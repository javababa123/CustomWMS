package com.rrl.wms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table(name = "RFS_SHIPMENT_LINE")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class ShipmentLine extends AuditableEntity<ShipmentLine> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String shipmentLineKey;

    @Column
    protected String articleCode;

    @Column
    protected Float quantity;

    @Column
    protected Float packedQuantity;

    @Column
    protected Integer lineNo;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "shipment_key", nullable=false)
    @JsonBackReference
    private Shipment shipment;

    public String getShipmentLineKey() {
        return shipmentLineKey;
    }

    public void setShipmentLineKey(String shipmentLineKey) {
        this.shipmentLineKey = shipmentLineKey;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getPackedQuantity() {
        return packedQuantity;
    }

    public void setPackedQuantity(Float packedQuantity) {
        this.packedQuantity = packedQuantity;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
