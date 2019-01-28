package com.accounts.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.app.entities.Account;
import com.accounts.app.services.IAccountService;

/**
 * The API Controller to manage the REST Services by mapping the requests
 * 
 * @author adanp
 *
 */
@RestController
@RequestMapping("/api/")
public class ApiController {

	/**
	 * Linking to the Account Service to retrive the data
	 */
	@Autowired
	private IAccountService accountSvc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * REST Service to generate the encoded string for the password
	 * 
	 * @param passwd
	 * @return String encodedPassword
	 */
	@RequestMapping(path = "generate_password/{password}", method = RequestMethod.GET)
	public String genPassword(@PathVariable(value = "password") String passwd) {
		return passwordEncoder.encode(passwd);
	}

	@RequestMapping(path = "account/", method = RequestMethod.GET)
	public List<Account> getAll() {
		List<Account> accounts = accountSvc.findAll();

		return accounts;
	}
	
	/**
	 * POST HTTP Request are utilized for CREATE operations
	 * @param account
	 */
	@RequestMapping(path = "account/", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody Optional<Account> account) {
		if (!account.isPresent()) {
			//Return error response when the account is not correct
		} 
		accountSvc.save(account.get());
	}
	
	/**
	 * PUT HTTP Request are utilized for UPDATE operations,
	 * both PUT and POST returns CREATED
	 * @param account
	 */
	@RequestMapping(path = "account/", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void save(@RequestBody Optional<Account> account) {
		if (!account.isPresent()) {
			//Return error response when the account is not valid account
		}
		if (!accountSvc.findOne(account.get().getId()).isPresent()) {
			//Return error response when the account is not already present
		}
		accountSvc.save(account.get());
	}

	@RequestMapping(path = "account/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteByID(@PathVariable(value = "id") Long id) {
		accountSvc.delete(id);
	}

	@RequestMapping(path = "account/{id}", method = RequestMethod.GET)
	public Optional<Account> getByID(@PathVariable(value = "id") Long id) {
		Optional<Account> account = accountSvc.findOne(id);

		return account;
	}

}
