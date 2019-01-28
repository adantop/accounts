package com.accounts.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.accounts.app.entities.Account;

/**
 * Interface for the account repository
 * 
 * @author adanp
 *
 */
public interface IAccountRepository extends CrudRepository<Account, Long> {

}
