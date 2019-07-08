package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.Comment;
import com.sarah.model.Response;

public interface ResponseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Response record);

    int insertSelective(Response record);

    Response selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Response record);

    int updateByPrimaryKey(Response record);
    List<Response> selectAll(Response record);
    List<Response> selectList(@Param("index")int index,@Param("rows")int rows);
}