package com.accounts.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accounts.app.entities.Account;
import com.accounts.app.repositories.IAccountRepository;

// TODO Add handling for update and create timestamps
@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private IAccountRepository accountDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Account> findAll() {
		return (List<Account>) accountDao.findAll();
	}

	@Override
	@Transactional
	public void save (Account account) {
		accountDao.save(account);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Account> findOne(Long id) {
		return accountDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		accountDao.deleteById(id);
	}

}