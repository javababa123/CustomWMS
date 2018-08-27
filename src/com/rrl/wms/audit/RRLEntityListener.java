package com.rrl.wms.audit;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class RRLEntityListener {

    @PostUpdate
    public void postUpdate(Object object){
        System.out.println("article post update=="+object.toString());
    }

    @PostPersist
    public void postPersist(Object object){
        System.out.println("article post update=="+object.toString());
    }
}
