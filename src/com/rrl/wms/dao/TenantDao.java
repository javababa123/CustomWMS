package com.rrl.wms.dao;

import com.rrl.wms.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TenantDao extends JpaRepository<Tenant, Serializable>{

    public Tenant findByTenantId(String tenantId);
}
