package com.duan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duan.dao.CategoryDao;
import com.duan.entity.Category;
import com.duan.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao cdao;

	/* Lấy tất cả loại sản phẩm hiển thị lên danh sách */ 
	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}
	
	
}
