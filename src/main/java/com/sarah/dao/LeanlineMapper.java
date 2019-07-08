package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.Leanline;
import com.sarah.model.LeanlineCategory;

public interface LeanlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leanline record);

    int insertSelective(Leanline record);

    Leanline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leanline record);

    int updateByPrimaryKey(Leanline record);
    
    List<Leanline> selectAll(Leanline record);
    
    List<Leanline> selectList(@Param("index")int index,@Param("name")String name,@Param("rows")int rows);

}