package com.grey.saas.master.service;

import com.grey.saas.events.SaveTenantEvent;
import com.grey.saas.master.model.MasterTenantEntity;
import com.grey.saas.master.repository.MasterTenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MasterTenantServiceImpl implements MasterTenantService {

    ApplicationEventPublisher eventPublisher;

    MasterTenantRepository repository;

    @Override
    public Optional<MasterTenantEntity> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId);
    }

    @Override
    public MasterTenantEntity save(MasterTenantEntity tenant) {
        var tenantEntity = repository.save(tenant);
        eventPublisher.publishEvent(new SaveTenantEvent(this, tenantEntity.getTenantId()));
        return tenantEntity;
    }

    @Override
    public List<MasterTenantEntity> findAll() {
        return repository.findAll();
    }
}
