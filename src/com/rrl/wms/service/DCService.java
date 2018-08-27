package com.rrl.wms.service;

import com.rrl.wms.entity.DC;

import java.util.List;

public interface DCService {

    public List<DC> findall();

    public DC createDC(DC dc);

    public DC findByDcCode(String dcCode);
}
