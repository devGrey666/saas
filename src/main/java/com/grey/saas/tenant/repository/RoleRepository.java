package com.grey.saas.tenant.repository;

import com.grey.saas.tenant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Query to find a Role entiry based on the {@link Role} name
     *
     * @param role
     * @return
     */
    Role findByRole(String role);
}
