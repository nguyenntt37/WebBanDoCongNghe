package com.duan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duan.entity.Category;




@Repository
public interface CategoryDao extends JpaRepository<Category, String>{

}
