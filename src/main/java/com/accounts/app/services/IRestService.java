/**
 * 
 */
package com.accounts.app.services;

import java.util.List;

import com.accounts.app.entities.Account;

/**
 * @author adanp
 *
 */
public interface IRestService {

	public Account findOne();

	public List<Account> findAll();
	
}
