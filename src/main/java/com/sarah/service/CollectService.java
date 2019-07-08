package com.sarah.service;

import java.util.List;

import com.sarah.model.Article;
import com.sarah.model.Collect;

public interface CollectService {
	int deleteByPrimaryKey(Integer id);

	int deleteByCollect(Collect record);
	
    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
    
    List<Collect> selectAll(Collect record);

    List<Article> selectByUser(int userid);
}
