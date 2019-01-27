package com.accounts.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .headers()
                .frameOptions()
                .disable()
                .and()
            .authorizeRequests()
                .antMatchers("/","/home", "/index", "/index.html", "/index.jsp", "/accounts").permitAll()
                .antMatchers(HttpMethod.GET, "/api/account/**").hasRole("READ")
                .antMatchers(HttpMethod.GET, "/test").hasRole("READ")
                .antMatchers(HttpMethod.GET, "/test").hasRole("READ")
                .antMatchers(HttpMethod.POST).hasRole("WRITE")
                .antMatchers(HttpMethod.PUT).hasRole("WRITE")
                .antMatchers(HttpMethod.DELETE).hasRole("WRITE");
        /**
         * GET    /api/account/   list accounts
         * GET    /api/account/** view one account
         * POST   /api/account/   new account
         * PUT    /api/account/** edit account
         * DELETE /api/accoun/**  delete account
         * POST   /save           save account
         * GET    /edit/**        edit account
         * GET    /delete         delete account
         * GET    /new            create account
         * GET    /test           
         */
    }


}