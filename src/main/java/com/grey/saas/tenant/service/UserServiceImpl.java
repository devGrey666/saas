package com.grey.saas.tenant.service;

import java.util.List;

import com.grey.saas.tenant.model.User;
import com.grey.saas.tenant.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory
            .getLogger(UserServiceImpl.class);

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public String findLoggedInUsername() {
        return "";
    }

    @Override
    public User findByUsernameAndTenantname(String username, String tenant) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

//    @Override
//    public User save(User user) {
//        // Encrypt the password
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        User justSavedUser = userRepository.save(user);
//        LOG.info("User:" + justSavedUser.getUsername() + " saved.");
//        return justSavedUser;
//    }
//
//    @Override
//    public String findLoggedInUsername() {
//        Object userDetails = SecurityContextHolder.getContext()
//                .getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            String username = ((UserDetails) userDetails).getUsername();
//            LOG.info("Logged in username:" + username);
//            return username;
//        }
//        return null;
//    }
//
//    @Override
//    public User findByUsernameAndTenantname(String username, String tenant) {
//        User user = userRepository.findByUsernameAndTenantname(username,
//                tenant);
//        if (user == null) {
//            throw new UsernameNotFoundException(
//                    String.format(
//                            "Username not found for domain, "
//                                    + "username=%s, tenant=%s",
//                            username, tenant));
//        }
//        LOG.info("Found user with username:" + user.getUsername()
//                + " from tenant:" + user.getTenant());
//        return user;
//    }
//
//    @Override
//    public List<User> findAllUsers() {
//        return userRepository.findAll();
//    }
}