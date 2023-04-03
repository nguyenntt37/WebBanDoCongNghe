package com.duan.service;

import java.util.List;

import com.duan.entity.Account;

public interface AccountService {
	public Account findById(String username);

	public List<Account> getAdministrators();

	public List<Account> findAll();
}
