package com.rrl.wms.service.impl;

import com.rrl.wms.dao.LocationDao;
import com.rrl.wms.entity.Location;
import com.rrl.wms.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    protected LocationDao locationDao;

    public List<Location> findall(){
        return locationDao.findAll();
    }

    public Location createLocation(Location location){
        return locationDao.save(location);
    }

    public Location findByLocationId(String locationId){
        return locationDao.findByLocationId(locationId);
    }
}
