package com.grey.saas.master.model;

import com.grey.saas.base.entities.BaseFullAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class MasterTenantEntity extends BaseFullAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "sub_domain", nullable = false)
    private String subDomain;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "no_of_process", nullable = false)
    private int noOfProcess;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "tenant_pwd")
    private String password;

    @Column(name = "tenant_url")
    private String url;

}
