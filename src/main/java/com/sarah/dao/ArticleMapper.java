package com.sarah.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sarah.model.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    List selectAll(Article record);
    
    List selectRand();
    
    List selectHotArticle();
    
    List selectByMH(Article record);
  //分页显示
    List<Article> seleteListSelective(@Param("index")int index, @Param("id")int id ,@Param("rows")int rows);
    //分类分页显示
    List<Article> seleteListByCategorySelective(@Param("index")int index, @Param("id")int id ,@Param("rows")int rows);
    //查询分页展示
    List<Article> seleteList(@Param("index")int index,@Param("title")String title,@Param("auther")String auther,@Param("rows")int rows);
}