/**
 * 
 */
package com.accounts.app.services;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accounts.app.entities.Account;

/**
 * This service must be utilized by the UI Controllers
 * to access the account information intead of using
 * the Account Service
 * 
 * @author adantop
 *
 */
@Service
public class RestServiceImpl implements IRestService {

	private final RestTemplate template;

	public RestServiceImpl(RestTemplateBuilder template) {
		this.template = template.build();
	}

	@Override
	public Account findOne() {

		return this.template.getForObject("http://localhost/api/account/{id}", Account.class, 1);
	}

	@Override
	public List<Account> findAll() {
		return null;
	}

}
