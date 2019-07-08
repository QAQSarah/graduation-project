package com.sarah.service;

import java.util.List;

import com.sarah.model.Article;
import com.sarah.model.Comment;
import com.sarah.model.CommentModel;
import com.sarah.util.PageBean;

public interface CommentService {
	int deleteByPrimaryKey(Integer id);

	int insert(Comment record);

	int insertSelective(Comment record);

	Comment selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Comment record);

	int updateByPrimaryKeyWithBLOBs(Comment record);

	int updateByPrimaryKey(Comment record);

	List<CommentModel> seleteByComment(Comment record);

	List<Article> selectByUser(int userid);
	
	PageBean<Comment> selectList(int pageNum,int rows);
}
