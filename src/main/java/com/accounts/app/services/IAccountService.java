package com.accounts.app.services;

import java.util.List;
import java.util.Optional;

import com.accounts.app.entities.Account;

public interface IAccountService {
	
	public List<Account> findAll ();
	
	public void save (Account account);
	
	public void delete (Long id);

	public Optional<Account> findOne(Long id);

}
