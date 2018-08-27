package com.rrl.wms.audit;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor(){
        return "Tushar11";
    }
}
