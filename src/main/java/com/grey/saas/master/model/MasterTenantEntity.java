package com.grey.saas.master.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class MasterTenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "tenant_id")
    private String tenantId;

    @Size(max = 256)
    @Column(name = "tenant_url")
    private String url;

    @Column(name = "user_name")
    private String username;

    /**
     * For simplicity, we are not storing an encrypted password. In production
     * this should be an encrypted password.
     */

    @Column(name = "tenant_pwd")
    private String password;

    /**
     * Specifies the version field or property of an entity class that serves as
     * its optimistic lock value. The version is used to ensure integrity when
     * performing the merge operation and for optimistic concurrency control.
     */
    @Version
    private int version = 0;
}
