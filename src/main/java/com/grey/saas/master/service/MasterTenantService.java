package com.grey.saas.master.service;

import com.grey.saas.master.model.MasterTenantEntity;

import java.util.List;
import java.util.Optional;

public interface MasterTenantService {

    Optional<MasterTenantEntity> findByTenantId(String tenantId);

    MasterTenantEntity save(MasterTenantEntity tenant);

    List<MasterTenantEntity> findAll();
}
