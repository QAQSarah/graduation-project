package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.Comment;
import com.sarah.util.PageBean;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> seleteByComment(Comment record);
    
    List<Comment> selectList(@Param("index")int index,@Param("rows")int rows);
    
}