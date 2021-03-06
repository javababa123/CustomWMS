package com.rrl.wms.config;

import com.rrl.wms.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RRLAuditConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }
}
