package com.duan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.dao.AccountDao;
import com.duan.dao.AuthorityDao;
import com.duan.entity.Account;
import com.duan.entity.Authority;
import com.duan.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
@Autowired AuthorityDao dao;
@Autowired AccountDao acdao;

public List<Authority> findAuthoritiesOfAdministrators() {
	List<Account> accounts = acdao.getAdministrators();
	return dao.authoritiesOf(accounts);
}

public List<Authority> findAll() {
	return dao.findAll();
}

public Authority create(Authority auth) {
	return dao.save(auth);
}

public void delete(Integer id) {
	dao.deleteById(id);
}
}
