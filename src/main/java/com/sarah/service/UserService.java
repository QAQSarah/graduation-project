package com.sarah.service;

import java.util.List;
import java.util.Map;

import com.sarah.model.Article;
import com.sarah.model.User;
import com.sarah.util.PageBean;

public interface UserService {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);
	
	String insertUser(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);
	
	Map selectByUser(User record);
	
	User selectBySelective(User record);
	
	User selectByAdmin(User record);
	
	List<User> selectAllUser(User record);

	String updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
	
	PageBean<User> seleteUserPageBean(int page, int rows);
	
	int updateUser(User record);
	PageBean<User> seleteSreach(int page, int rows,User user);
	

	
}
