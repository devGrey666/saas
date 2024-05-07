package com.grey.saas.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    @Override
    public UserDetails loadUserByUsernameAndTenantname(String username, String tenantName) throws UsernameNotFoundException {
        return null;
    }
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsernameAndTenantname(String username,
//                                                       String tenant) throws UsernameNotFoundException {
//        if (StringUtils.isAnyBlank(username, tenant)) {
//            throw new UsernameNotFoundException(
//                    "Username and domain must be provided");
//        }
//        // Look for the user based on the username and tenant by accessing the
//        // UserRepository via the UserService
//        User user = userService.findByUsernameAndTenantname(username, tenant);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(
//                    String.format(
//                            "Username not found for domain, "
//                                    + "username=%s, tenant=%s",
//                            username, tenant));
//        }
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//
//        CustomUserDetails customUserDetails = new CustomUserDetails(
//                user.getUsername(), user.getPassword(), grantedAuthorities,
//                tenant);
//
//        return customUserDetails;
//    }
}
