package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.resource.spi.AuthenticationMechanism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ArticleMapper;
import com.sarah.dao.CategoryMapper;
import com.sarah.model.Article;
import com.sarah.model.Category;
import com.sarah.service.CategoryService;
import com.sarah.util.PageBean;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ArticleMapper articleMapper;

	public List<Category> showcategores() {
		Category category=new Category();
		List<Category> list=categoryMapper.selectAll(category);
		return list;
	}

	public PageBean<Category> seleteCategorys(int pageNum, int rows, Category category) {
		List<Category> all=categoryMapper.selectAll(category);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<Category> pb = new PageBean<Category>(pageNum, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		List<Category> list=new ArrayList<Category>();
		list=categoryMapper.selectCategoryByPage(startIndex ,category.getCategoryname(), rows);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

	public int insertCategory(Category category) {
		return categoryMapper.insert(category);
	}

	public Category selectById(int id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	public int updateCategory(Category category) {
		return categoryMapper.updateByPrimaryKey(category);
	}

	public int deleteByPrimaryKey(int id) {
		int val=0;
		Article article=new Article();
		article.setCategoryid(id);
		List<Article> list=articleMapper.selectAll(article);
		if(list.size()<=0) {
		val=categoryMapper.deleteByPrimaryKey(id);
		}
		return val;
	}

}
