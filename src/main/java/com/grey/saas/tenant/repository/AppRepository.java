package com.grey.saas.tenant.repository;

import com.grey.saas.tenant.model.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Long> {
    Optional<AppEntity> findByName(String appName);

    List<AppEntity> findAll();
}
