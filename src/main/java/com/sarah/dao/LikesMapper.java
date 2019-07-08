package com.sarah.dao;

import java.util.List;

import com.sarah.model.Likes;

public interface LikesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
    
    List<Likes> selectAll(Likes record);
}