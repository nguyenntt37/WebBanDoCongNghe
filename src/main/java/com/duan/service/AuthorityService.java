package com.duan.service;

import java.util.List;

import com.duan.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAuthoritiesOfAdministrators();

	public List<Authority> findAll();

	public Authority create(Authority auth);
	
	public void delete(Integer id);

}
