package com.sarah.service;

import java.util.List;

import com.sarah.model.Leanline;
import com.sarah.model.LeanlineCategory;
import com.sarah.util.PageBean;

public interface LeanService {
	int deleteByPrimaryKey(Integer id);

	int insert(Leanline record);

	int insertSelective(Leanline record);

	Leanline selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Leanline record);

	int updateByPrimaryKey(Leanline record);

	List<Leanline> seleteAll(Leanline record);
	
	PageBean<Leanline> selectList(int page, int rows, Leanline leanline);
}
