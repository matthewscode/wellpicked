package com.puppey.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.puppey.service.UserService;
import com.puppey.util.SiteUser;

@Component
public class UserSuccessHandler implements UserDetailsService {

    @Autowired
    UserService userService;

    private static final Logger LOG = Logger.getLogger(OpenIdUserSuccessHandler.class);

    @Override
    public UserDetails loadUserByUsername(String userId) {

        com.puppey.domain.User user;
        List<GrantedAuthority> authorities;

        try {
            user = userService.getUserByEmail(userId);
            System.out.println(user.getCurrency());

        } catch (Exception e) {
            LOG.error("database error", e);
            throw new UsernameNotFoundException("database error ");
        }
        authorities = userService.getAuthorities(user);
        return buildUserFromUserEntity(user, authorities);
    }

    static User buildUserFromUserEntity(com.puppey.domain.User user, List<GrantedAuthority> authorities) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        SiteUser siteUser = new SiteUser(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        siteUser.setUserId(user.getUserId());
        return siteUser;
    }

}
