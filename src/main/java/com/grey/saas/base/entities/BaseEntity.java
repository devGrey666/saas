package com.grey.saas.base.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

@Getter
@DynamicUpdate
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -166799999669043232L;
}
