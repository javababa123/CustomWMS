package com.rrl.wms.service;

import com.rrl.wms.entity.Tenant;
import java.util.List;

public interface TenantService {

    public List<Tenant> getAllTenants();

    public  Tenant createTenant(Tenant tenant);

    public Tenant findTenant(String tenantId);
}
