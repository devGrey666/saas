package com.grey.saas.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SaveTenantEvent extends ApplicationEvent {
    private final String tenantId;

    public SaveTenantEvent(Object source, String tenantId) {
        super(source);
        this.tenantId = tenantId;
    }

}
