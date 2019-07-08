package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.Category;
import com.sarah.model.LeanlineCategory;

public interface LeanlineCategoryMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(LeanlineCategory record);

	int insertSelective(LeanlineCategory record);

	LeanlineCategory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(LeanlineCategory record);

	int updateByPrimaryKey(LeanlineCategory record);

	List<LeanlineCategory> selectAll(LeanlineCategory record);
	
	List<LeanlineCategory> selectList(@Param("index")int index,@Param("name")String name,@Param("rows")int rows);
}