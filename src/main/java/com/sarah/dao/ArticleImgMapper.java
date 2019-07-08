package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.ArticleImg;

public interface ArticleImgMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ArticleImg record);

	int insertSelective(ArticleImg record);

	ArticleImg selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ArticleImg record);

	int updateByPrimaryKey(ArticleImg record);

	List<ArticleImg> selectByArticleImg(ArticleImg record);

	List<ArticleImg> selectList(@Param("index")int index,@Param("rows")int rows);
}