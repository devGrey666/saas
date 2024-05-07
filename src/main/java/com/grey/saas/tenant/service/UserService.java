package com.grey.saas.tenant.service;

import com.grey.saas.tenant.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    User save(User user);

    String findLoggedInUsername();

    @Query("select p from User p where p.username = :username and p.tenant = :tenant")
    User findByUsernameAndTenantname(@Param("username") String username,
                                     @Param("tenant") String tenant);

    List<User> findAllUsers();
}
