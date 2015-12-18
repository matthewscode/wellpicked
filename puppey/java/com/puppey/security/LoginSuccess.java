package com.puppey.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.puppey.service.UserService;
import com.puppey.util.SiteUser;

@SessionAttributes("user")
@Component
public class LoginSuccess extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    
    private static final Logger LOG = Logger.getLogger(LoginSuccess.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
        SiteUser su = (SiteUser) authentication.getPrincipal();
        userService.updateLastLogin(su.getUserId());
        try {
            super.onAuthenticationSuccess(request, response, authentication);
        } catch (IOException | ServletException e) {
            LOG.error("Unable to forward authentication success", e);
        }

    }
}
