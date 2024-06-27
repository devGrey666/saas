package com.grey.saas.master.repository;

import com.grey.saas.master.model.MasterTenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenantEntity, Long> {
    Optional<MasterTenantEntity> findByTenantId(String tenantId);

    List<MasterTenantEntity> findAll();
}
