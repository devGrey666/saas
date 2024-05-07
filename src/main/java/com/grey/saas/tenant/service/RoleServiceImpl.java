package com.grey.saas.tenant.service;

import com.grey.saas.tenant.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOG = LoggerFactory
            .getLogger(RoleServiceImpl.class);

//    @Autowired
//    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String roleName) {
//        Role role = roleRepository.findByRole(roleName);
////        LOG.info("Role:" + role.getRole() + " found");
//        return role;
        return null;
    }
}
