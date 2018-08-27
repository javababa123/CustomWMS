package com.rrl.wms.service.impl;

import com.rrl.wms.dao.TenantDao;
import com.rrl.wms.entity.Tenant;
import com.rrl.wms.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantDao tenantDao;

    public List<Tenant> getAllTenants(){
        return tenantDao.findAll();
    }

    public  Tenant createTenant(Tenant tenant){
        return tenantDao.save(tenant);
    }

    public Tenant findTenant(String tenantId){
        return tenantDao.findByTenantId(tenantId);
    }
}
