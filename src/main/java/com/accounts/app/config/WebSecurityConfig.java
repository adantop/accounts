package com.accounts.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The security configuration for the spring application, in this
 * class we are loading the users and creating the Authentification
 * Manager
 * 
 * @author adantop
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
        	.headers().frameOptions().sameOrigin()
        	.and()
	        .csrf().disable()
	        .httpBasic()
	        .and()
	        .authorizeRequests()
		        .antMatchers(HttpMethod.OPTIONS).permitAll()
		        .antMatchers(
		        	"/",
		        	"/home", 
		        	"/index", 
		        	"/index.html", 
		        	"/index.jsp", 
		        	"/accounts", 
		        	"/h2-console/**", 
		        	"/swagger-ui.html**", 
		        	"/swagger-resources/**",
		        	"/v2/api-docs/**",
		        	"/generate_password/**",
		        	"/api").permitAll()
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