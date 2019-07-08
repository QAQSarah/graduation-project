package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.User;
import com.sarah.util.PageBean;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> selectAll(User record);

	List<String> selectUserName(User record);

	List<String> selectEmail(User record);

	/**
	 * 分页查询
	 * 
	 * @param index
	 * @param rows
	 * @return
	 */
	List<User> selectUserPage(@Param("index") int index, @Param("rows") int rows);

	List<User> selectSreachUser(@Param("index") int startIndex, @Param("rows")int rows,@Param("name") String name,@Param("exmail") String exmail, @Param("status")Integer status);

}