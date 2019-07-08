package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ArticleImgMapper;
import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.service.ArticleImgService;
import com.sarah.service.ArticleService;
import com.sarah.util.PageBean;

@Service
public class ArticleImgServiceImpl implements ArticleImgService {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleImgMapper articleImgMapper;

	public int deleteByPrimaryKey(Integer id) {
		return articleImgMapper.deleteByPrimaryKey(id);
	}

	public int insert(ArticleImg record) {
		return articleImgMapper.insertSelective(record);
	}

	public int insertSelective(ArticleImg record) {

		return 0;
	}

	public ArticleImg selectByPrimaryKey(Integer id) {
		return articleImgMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ArticleImg record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(ArticleImg record) {
		return articleImgMapper.updateByPrimaryKey(record);
	}

	public ArticleImg selectByArticle( Article article) {
		ArticleImg articleImg = new ArticleImg();
		articleImg.setArticleid(article.getId());
		List<ArticleImg> aImgs = articleImgMapper.selectByArticleImg(articleImg);
		if(aImgs.size()>0) {
			return aImgs.get(0);
		}
		return null;
	}
	public List<ArticleImg> selectByArticleList( Article article) {
		ArticleImg articleImg = new ArticleImg();
		articleImg.setArticleid(article.getId());
		List<ArticleImg> aImgs = articleImgMapper.selectByArticleImg(articleImg);
		if(aImgs.size()>0) {
			return aImgs;
		}
		return null;
	}

	public PageBean<ArticleImg> selectList(int pageNum, int row) {
		ArticleImg articleImg=new ArticleImg();
		List<ArticleImg> all = articleImgMapper.selectByArticleImg(articleImg);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<ArticleImg> pb = new PageBean<ArticleImg>(pageNum, row, totalRecord);
		int startIndex = pb.getStartIndex();
		 List<ArticleImg> list = articleImgMapper.selectList(startIndex , row);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

}
