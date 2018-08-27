package com.rrl.wms.entity;


import com.rrl.wms.audit.RRLEntityListener;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table(name = "RFS_TENANT")
@EnableJpaAuditing
@EntityListeners(RRLEntityListener.class)
public class Tenant extends AuditableEntity<Tenant> {

    @Id
    @GenericGenerator(name = "seq", strategy = "com.rrl.wms.util.KeyGenerator")
    @GeneratedValue(generator="seq")
    protected String tenantKey;

    @Column
    protected String tenantId;

    public String getTenantKey() {
        return tenantKey;
    }

    public void setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + tenantId +
                '}';
    }
}
