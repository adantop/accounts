package com.accounts.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private String resourceId = "spring-oauth";
	
	@Value("${config.oauth2.clientID}")
    private String client;
	
	@Value("${config.oauth2.clientSecret}")
    private String secret;
	
	@Value("${config.oauth2.accessTokenUri}")
	private String accessTokenUri;
	
	@Value("${config.oauth2.userAuthorizationUri}")
	private String userAuthUri;
	
	private SecurityScheme securityScheme() {
	    GrantType grantType = new AuthorizationCodeGrantBuilder()
	        .tokenEndpoint(new TokenEndpoint(accessTokenUri, "access_token"))
	        .tokenRequestEndpoint(
	          new TokenRequestEndpoint(userAuthUri, client, secret))
	        .build();
	 
	    SecurityScheme oauth = new OAuthBuilder().name(resourceId)
	        .grantTypes(Arrays.asList(grantType))
	        .scopes(Arrays.asList(scopes()))
	        .build();
	    return oauth;
	}
	
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("read", "for read operations"), 
	      new AuthorizationScope("write", "for write operations"), 
	      new AuthorizationScope("trust", "for admin operations") };
	    return scopes;
	}
	
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	      .securityReferences(
	        Arrays.asList(new SecurityReference(resourceId, scopes())))
	      .forPaths(PathSelectors.regex("/api/account/*"))
	      .build();
	}

	@Bean
	public Docket accountApi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName("Accounts API")
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.ant("/api/account/*"))
					.build()
						.securitySchemes(Arrays.asList(securityScheme()))
						.securityContexts(Arrays.asList(securityContext()));
	}
	
	@Bean
	public Docket passwordApi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName("Password Generator")
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.ant("/api/generate_password/*"))
					.build();
	}
	
	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId(client)
				.clientSecret(secret)
				.scopeSeparator(" ")
				.useBasicAuthenticationWithAccessCodeGrant(true)
				.build();
	}
}