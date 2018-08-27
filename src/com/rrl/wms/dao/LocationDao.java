package com.rrl.wms.dao;


import com.rrl.wms.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface  LocationDao extends JpaRepository<Location, Serializable>{

    public Location findByLocationId(String locationId);
}
