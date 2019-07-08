package com.sarah.service;

import java.util.List;

import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.util.PageBean;

public interface ArticleImgService {

	int deleteByPrimaryKey(Integer id);

    int insert(ArticleImg record);

    int insertSelective(ArticleImg record);

    ArticleImg selectByPrimaryKey(Integer id);
    
    ArticleImg selectByArticle(Article article);

    int updateByPrimaryKeySelective(ArticleImg record);

    int updateByPrimaryKey(ArticleImg record);
    
    List<ArticleImg> selectByArticleList( Article article);
    
    PageBean<ArticleImg> selectList(int pageNum,int row);
}
