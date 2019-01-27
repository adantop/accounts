package com.accounts.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.accounts.app.entities.Account;

public interface IAccountRepository extends CrudRepository<Account, Long> {

}
