package com.sarah.service;

import java.util.List;

import com.sarah.model.Article;
import com.sarah.util.PageBean;

public interface ArticleService {

	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

	List<Article> showArticles();

	List<Article> selectRand();

	List<Article> selectHotArticle();

	PageBean<Article> seleteByCategory(int page, int rows, int id);
	
	PageBean<Article> seleteArticles(int page, int rows, Article article);

	List<Article> selectByTitle(String title);
}
