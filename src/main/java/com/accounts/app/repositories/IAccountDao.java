package com.accounts.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.accounts.app.models.entities.Account;

public interface IAccountDao extends CrudRepository<Account, Long> {

}
