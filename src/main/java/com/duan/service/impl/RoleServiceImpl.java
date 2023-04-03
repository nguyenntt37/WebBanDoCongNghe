package com.duan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.dao.RoleDao;
import com.duan.entity.Role;
import com.duan.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired RoleDao dao;
	
	public List<Role> findAll() {
		return dao.findAll();
	}
}
