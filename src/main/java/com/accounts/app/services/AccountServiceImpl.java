package com.accounts.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accounts.app.models.entities.Account;
import com.accounts.app.repositories.IAccountDao;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private IAccountDao accountDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return (List<Account>) accountDao.findAll();
	}

	@Override
	@Transactional
	public void save (Account account) {
		// TODO Auto-generated method stub
		accountDao.save(account);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Account> findOne(Long id) {
		// TODO Auto-generated method stub
		return accountDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		accountDao.deleteById(id);
	}

}