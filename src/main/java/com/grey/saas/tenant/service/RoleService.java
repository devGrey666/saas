package com.grey.saas.tenant.service;

import com.grey.saas.tenant.model.Role;

public interface RoleService {

    Role findByRole(String roleName);
}