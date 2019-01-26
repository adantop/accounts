/**
 * 
 */
package com.accounts.app.services;

import java.util.List;

import com.accounts.app.models.entities.Account;

/**
 * @author adanp
 *
 */
public interface IRestService {
	
	//public List<Account> findAll();

	public Account findOne();

	public List<Account> findAll();
	
}
