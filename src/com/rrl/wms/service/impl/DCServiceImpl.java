package com.rrl.wms.service.impl;

import com.rrl.wms.dao.DCDao;
import com.rrl.wms.entity.DC;
import com.rrl.wms.service.DCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DCServiceImpl implements DCService {

    @Autowired
    DCDao dcDao;

    public List<DC> findall(){
        return dcDao.findAll();
    }

    public DC createDC(DC dc){
       return dcDao.save(dc);
    }

    public DC findByDcCode(String dcCode){
      return  dcDao.findByDcCode(dcCode);
    }
}
