/**
 * 
 */
package com.accounts.app.services;

import java.util.List;

import com.accounts.app.entities.Account;

/**
 * Should be merged with the AccountService, this is duplicated
 * @author adantop
 *
 */
public interface IRestService {

	public Account findOne();

	public List<Account> findAll();
	
}
