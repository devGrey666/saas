package com.grey.saas.tenant.config;

import com.grey.saas.events.SaveTenantEvent;
import com.grey.saas.master.model.MasterTenantEntity;
import com.grey.saas.master.repository.MasterTenantRepository;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@Getter
@Setter
@Slf4j
@Component
public class DynamicDataSourceBasedMultiTenantSpringLiquibase implements InitializingBean, ResourceLoaderAware, ApplicationListener<SaveTenantEvent> {

    @Autowired
    private MasterTenantRepository masterTenantRepository;

    @Autowired
    @Qualifier("tenantLiquibaseProperties")
    private LiquibaseProperties liquibaseProperties;

    @Value("${spring.datasource.url-prefix}")
    private String urlPrefix;

////    @Value("${encryption.secret}")
//    private String secret;
//
////    @Value("${encryption.salt}")
//    private String salt;

    private ResourceLoader resourceLoader;

    @Override
    public void afterPropertiesSet() {
        log.info("DynamicDataSources based multitenancy enabled");
        this.runOnAllTenants(masterTenantRepository.findAll());
    }

    protected void runOnAllTenants(Collection<MasterTenantEntity> tenants) {
        for (MasterTenantEntity tenant : tenants) {
            log.info("Initializing Liquibase for tenant " + tenant.getTenantId());
            log.info("Url Prefix " + urlPrefix);
//            String decryptedPassword = encryptionService.decrypt(tenant.getPassword(), secret, salt);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                log.error("Class No Found ", e);
            }

            Properties properties = new Properties();
            properties.setProperty("password", tenant.getPassword());
            properties.setProperty("user", tenant.getUsername());
            try (Connection connection = DriverManager.getConnection(urlPrefix + tenant.getTenantId() + "?createDatabaseIfNotExist=true", properties)) {
                DataSource tenantDataSource = new SingleConnectionDataSource(connection, false);
                SpringLiquibase liquibase = this.getSpringLiquibase(tenantDataSource);
                liquibase.afterPropertiesSet();
            } catch (SQLException | LiquibaseException e) {
                log.error("Failed to run Liquibase for tenant " + tenant.getTenantId(), e);
            }
            log.info("Liquibase ran for tenant " + tenant.getTenantId());
        }
    }

    protected SpringLiquibase getSpringLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setResourceLoader(getResourceLoader());
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/liquibase/tenants.yaml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
        liquibase.setLiquibaseTablespace(liquibaseProperties.getLiquibaseTablespace());
        liquibase.setDatabaseChangeLogTable(liquibaseProperties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(liquibaseProperties.getDatabaseChangeLogLockTable());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
//        liquibase.setLabels(liquibaseProperties.getLabels());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        liquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
        liquibase.setTestRollbackOnUpdate(liquibaseProperties.isTestRollbackOnUpdate());
        return liquibase;
    }

    @Override
    public void onApplicationEvent(SaveTenantEvent event) {
        var tenant = masterTenantRepository.findByTenantId(event.getTenantId());
        if (tenant.isPresent()) {
            List<MasterTenantEntity> list = new ArrayList<>();
            list.add(tenant.get());
            this.runOnAllTenants(list);
        }
    }
}
