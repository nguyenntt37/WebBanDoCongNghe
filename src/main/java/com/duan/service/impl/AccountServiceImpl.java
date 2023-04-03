package com.duan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.dao.AccountDao;
import com.duan.entity.Account;
import com.duan.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired AccountDao dao;

	@Override
	public Account findById(String username) {
		
		return dao.findById(username).get();
	}

	
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

	
	public List<Account> findAll() {
		return dao.findAll();
	}
	
	
	

}
