package com.grey.saas.tenant.service;

import com.grey.saas.tenant.model.AppEntity;
import com.grey.saas.tenant.repository.AppRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private AppRepository appRepository;

    @Override
    public AppEntity save(AppEntity appEntity) {
        return appRepository.save(appEntity);
    }

    @Override
    public List<AppEntity> findAll() {
        return appRepository.findAll();
    }
}
