package com.sarah.service;

import java.util.List;

import com.sarah.model.Article;
import com.sarah.model.Likes;

public interface LikesService {
	int deleteByPrimaryKey(Likes likes);

	int insert(Likes record);

	int insertSelective(Likes record);

	Likes selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Likes record);

	int updateByPrimaryKey(Likes record);
	
	List<Likes> selectAll(Likes record);
	
	List<Article> selectByUser(int userid);
}
