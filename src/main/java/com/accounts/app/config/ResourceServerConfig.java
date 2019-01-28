package com.accounts.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${config.oauth2.publicKey}")
    private String publicKey;

    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Value("${config.oauth2.resource.id}")
    private String resourceId;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable()
	        .authorizeRequests()
		        .antMatchers(HttpMethod.OPTIONS).permitAll()
		        .antMatchers("/","/home", "/index", "/index.html", "/index.jsp", "/accounts", "/h2-console/**").permitAll()
		        .antMatchers("/oauth/**", "/login").permitAll().anyRequest().authenticated()
                .antMatchers(HttpMethod.GET, "/api/account/**").authenticated()//.hasRole("READ")
                .antMatchers(HttpMethod.GET, "/test").authenticated()//.hasRole("READ")
                .antMatchers(HttpMethod.GET, "/test").authenticated()//.hasRole("READ")
                .antMatchers("/delete/**", "/save", "/new").authenticated()//.hasRole("WRITE")
                .antMatchers(HttpMethod.POST).authenticated()//.hasRole("WRITE")
                .antMatchers(HttpMethod.PUT).authenticated()//.hasRole("WRITE")
                .antMatchers(HttpMethod.DELETE).authenticated()//.hasRole("WRITE")
                .and()
                .formLogin().permitAll();
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