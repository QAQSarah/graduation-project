package com.sarah.service;

import java.util.List;

import com.sarah.model.Category;
import com.sarah.model.LeanlineCategory;
import com.sarah.util.PageBean;

public interface LeanLineService {

	int deleteByPrimaryKey(Integer id);

	int insert(LeanlineCategory record);

	int insertSelective(LeanlineCategory record);

	LeanlineCategory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(LeanlineCategory record);

	int updateByPrimaryKey(LeanlineCategory record);
	
	List<LeanlineCategory> seleteAll(LeanlineCategory record);
	
	PageBean<LeanlineCategory> selectList(int page, int rows, LeanlineCategory leanlineCategory);
}
