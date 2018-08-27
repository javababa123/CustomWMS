package com.rrl.wms.service;

import com.rrl.wms.entity.Location;

import java.util.List;

public interface LocationService {

    public List<Location> findall();

    public Location createLocation(Location location);

    public Location findByLocationId(String locationId);
}
