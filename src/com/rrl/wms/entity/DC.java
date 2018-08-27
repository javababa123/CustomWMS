package com.rrl.wms.entity;

import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table(name = "RFS_DC")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class DC extends AuditableEntity<DC> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String dcKey;

    @Column
    protected String dcCode;

    @Column
    protected String dcType;

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

    public String getDcKey() {
        return dcKey;
    }

    public void setDcKey(String dcKey) {
        this.dcKey = dcKey;
    }

    public String getDcCode() {
        return dcCode;
    }

    public void setDcCode(String dcCode) {
        this.dcCode = dcCode;
    }

    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
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
        return "DC{" +
                "code=" + dcCode +
                ", type='" + dcType + '\'' +
                '}';
    }
}
