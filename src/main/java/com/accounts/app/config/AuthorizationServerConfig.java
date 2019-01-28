/**
 * 
 */
package com.accounts.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * The role of the Authorization Server is to middle betweent the 
 * resource and the client, the client must communicate to the Authorization Server
 * to obtain the authentification, only after this the client will be able
 * to access the resources
 * @author adantop
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${config.oauth2.clientID}")
    private String client;
	
	@Value("${config.oauth2.clientSecret}")
    private String secret;
	
	@Value("${config.oauth2.resource.id}")
	private String resourceId;
	
	@Value("${config.oauth2.tokenTimeout}")
    private int expiration;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
            .withClient(client)
            .authorizedGrantTypes("client_credentials", "password", "refresh_token", "authorization_code")
            .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .resourceIds(resourceId)
            .accessTokenValiditySeconds(expiration)
            .secret(passwordEncoder.encode(secret));
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}
}
