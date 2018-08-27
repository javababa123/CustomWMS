package com.rrl.wms.service;

public interface TibcoJMSClient {
    public void send(String msg);
    public String receive();
}
