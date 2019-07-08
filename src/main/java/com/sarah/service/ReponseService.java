package com.sarah.service;

import java.util.List;

import com.sarah.model.ReponseModel;
import com.sarah.model.Response;
import com.sarah.util.PageBean;

public interface ReponseService {
	 	int deleteByPrimaryKey(Integer id);

	    int insert(Response record);

	    int insertSelective(Response record);

	    Response selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(Response record);

	    int updateByPrimaryKey(Response record);
	    List<ReponseModel> selectAll(Response record);
	    List<Response> selectResponseAll(Response record);
	    PageBean<Response> selectList(int pageNum,int rows);
}
