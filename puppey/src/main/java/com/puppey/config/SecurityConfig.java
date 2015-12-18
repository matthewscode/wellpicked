package com.puppey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import com.puppey.security.LoginSuccess;
import com.puppey.security.OpenIdUserSuccessHandler;
import com.puppey.security.UserSuccessHandler;

@Configuration
@ComponentScan(basePackages = { "com.puppey" })
@EnableWebSecurity
@Order(5)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // check if this works
        auth.userDetailsService(userSuccessHandler()).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").and().formLogin()
                .loginPage("/login").usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/profile").failureUrl("/403").successHandler(loginSucess())
                .and().openidLogin().authenticationUserDetailsService(openIdUserSuccessHandler()).permitAll().loginPage("/login").failureUrl("/403")
                .and().logout().deleteCookies("remove")
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and().csrf().disable();
    }
    
    

    @Bean
    protected UserSuccessHandler userSuccessHandler() {
        UserSuccessHandler ush = new UserSuccessHandler();
        return ush;
    }

    @Bean
    protected BCryptPasswordEncoder encoder() {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder(11);
        return enc;
    }

    @Bean
    protected LoginSuccess loginSucess() {
        return new LoginSuccess();
    }
    
    @Bean
    protected DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        return new DefaultWebSecurityExpressionHandler();
    }
    
    @Bean
    protected OpenIdUserSuccessHandler openIdUserSuccessHandler(){
    	return new OpenIdUserSuccessHandler();
    }
}
