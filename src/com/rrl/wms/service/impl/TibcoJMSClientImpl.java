package com.rrl.wms.service.impl;

import com.rrl.wms.integration.TibcoConsumer;
import com.rrl.wms.integration.TibcoSender;
import com.rrl.wms.service.TibcoJMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TibcoJMSClientImpl implements TibcoJMSClient {

    @Autowired
    TibcoConsumer tibcoConsumer;

    @Autowired
    TibcoSender tibcoSender;

    @Override
    public void send(String msg) {
        tibcoSender.send(msg);
    }

    @Override
    public String receive() {
        return tibcoConsumer.receive();
    }

}