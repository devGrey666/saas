package com.grey.saas.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@DynamicUpdate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseFullAudit implements Serializable {

    @Serial
    private static final long serialVersionUID = 719784638990372601L;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @CreatedBy
    @Column(nullable = false, updatable = false, name = "created_by")
    private long createdBy;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @LastModifiedBy
    @Column(nullable = false, name = "updated_by")
    private long updatedBy;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false", name = "is_deleted")
    private boolean isDeleted = false;

}
