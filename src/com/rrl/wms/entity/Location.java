package com.rrl.wms.entity;


import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table(name = "RFS_LOCATION")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class Location extends AuditableEntity<Location> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String locationKey;

    @Column
    protected String locationId;

    @Column
    protected String tenantId;

    public String getLocationKey() {
        return locationKey;
    }

    public void setLocationKey(String locationKey) {
        this.locationKey = locationKey;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + locationId +
                ", tenant ='" + tenantId + '\'' +
                '}';
    }

}
