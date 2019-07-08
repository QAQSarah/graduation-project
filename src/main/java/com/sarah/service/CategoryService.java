package com.sarah.service;

import java.util.List;

import com.sarah.model.Article;
import com.sarah.model.Category;
import com.sarah.util.PageBean;

public interface CategoryService {
	List<Category> showcategores();
	PageBean<Category> seleteCategorys(int page, int rows, Category category);
	int insertCategory(Category category);
	Category selectById(int id);
	int updateCategory(Category category); 
	int deleteByPrimaryKey(int id);
}
