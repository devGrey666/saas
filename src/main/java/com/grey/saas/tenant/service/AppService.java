package com.grey.saas.tenant.service;

import com.grey.saas.tenant.model.AppEntity;

import java.util.List;

public interface AppService {

    AppEntity save(AppEntity appEntity);

    List<AppEntity> findAll();

}
