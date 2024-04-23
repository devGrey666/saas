package com.grey.saas.tenant.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import com.grey.saas.master.model.MasterTenantEntity;
import com.grey.saas.master.repository.MasterTenantRepository;
import com.grey.saas.master.util.DataSourceUtil;
import com.grey.saas.master.util.TenantContextHolder;
import com.grey.saas.tenant.helper.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl{
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);

    private static final long serialVersionUID = 1L;

    /**
     * Injected MasterTenantRepository to access the tenant information from the master_tenant table
     */
    @Autowired
    private MasterTenantRepository masterTenantRepo;

    /**
     * Map to store the tenant ids as key and the data source as the value
     */
    private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        // This method is called more than once. So check if the data source map
        // is empty. If it is then rescan master_tenant table for all tenant
        // entries.
        if (dataSourcesMtApp.isEmpty()) {
            List<MasterTenantEntity> masterTenants = masterTenantRepo.findAll();
            LOG.info(">>>> selectAnyDataSource() -- Total tenants:" + masterTenants.size());
            for (MasterTenantEntity masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        if(this.dataSourcesMtApp.isEmpty()) return null;
        return this.dataSourcesMtApp.values().iterator().next();
    }



    @Override
    protected DataSource selectDataSource(Object tenantIdentifier) {
        // If the requested tenant id is not present check for it in the master
        // database 'master_tenant' table

        tenantIdentifier = initializeTenantIfLost(tenantIdentifier);

        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            List<MasterTenantEntity> masterTenants = masterTenantRepo.findAll();
            LOG.info(
                    ">>>> selectDataSource() -- tenant:" + tenantIdentifier + " Total tenants:" + masterTenants.size());
            for (MasterTenantEntity masterTenant : masterTenants) {
                if (this.dataSourcesMtApp.containsKey(masterTenant.getTenantId())) {
                    continue;
                }
                dataSourcesMtApp.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }
        //check again if tenant exist in map after rescan master_db, if not, throw UsernameNotFoundException
        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            LOG.warn("Trying to get tenant:" + tenantIdentifier + " which was not found in master db after rescan");
            throw new UsernameNotFoundException(
                    String.format(
                            "Tenant not found after rescan, "
                                    + " tenant=%s",
                            tenantIdentifier));
        }
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }

    /**
     * Initialize tenantId based on the logged in user if the tenant Id got lost in after form submission in a user
     * session.
     *
     * @param tenantIdentifier
     * @return tenantIdentifier
     */
    private Object initializeTenantIfLost(Object tenantIdentifier) {
        if (TenantContextHolder.getTenant() == null) {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            CustomUserDetails customUserDetails = null;
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                customUserDetails = principal instanceof CustomUserDetails ? (CustomUserDetails) principal : null;
            }
            assert customUserDetails != null;
            TenantContextHolder.setTenantId(customUserDetails.getTenant());
        }

        if (tenantIdentifier != TenantContextHolder.getTenant()) {
            tenantIdentifier = TenantContextHolder.getTenant();
        }
        return tenantIdentifier;
    }
}
