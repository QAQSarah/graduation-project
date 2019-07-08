package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ArticleMapper;
import com.sarah.dao.LikesMapper;
import com.sarah.model.Article;
import com.sarah.model.Likes;
import com.sarah.service.LikesService;

@Service
public class LikesServiceImpl implements LikesService {
	@Autowired
	private LikesMapper likesMapper;
	@Autowired
	private ArticleMapper articleMapper;

	public int insert(Likes record) {
		int r=0;
		if(record!=null) {
			List<Likes> list=selectAll(record);
			if(list.size()==0) {
				r=likesMapper.insert(record);
			}
		}
		return r;
	}

	public int insertSelective(Likes record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Likes selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Likes record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Likes record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByPrimaryKey(Likes likes) {
		List<Likes> list=new ArrayList<Likes>();
		int r=0;
		if(likes!=null) {
			list=selectAll(likes);
			Likes l=new Likes();
			if(list.size()!=0) {
				l=list.get(0);
				r=likesMapper.deleteByPrimaryKey(l.getId());
			}
		}
		return r;
	}

	public List<Likes> selectAll(Likes record) {
		List<Likes> list=new ArrayList<Likes>();
		if(record!=null) {
			list=likesMapper.selectAll(record);
					}
		return list;
	}

	public List<Article> selectByUser(int userid) {
		Likes likes=new Likes();
		List<Likes> likes2=new ArrayList<Likes>();
		List<Article> articles=new ArrayList<Article>();
		if(userid!=0) {
			likes.setUid(userid);
			likes2=likesMapper.selectAll(likes);
		}
		if(likes2.size()!=0) {
			for (Likes likes3 : likes2) {
				Article article=new Article();
				article.setId(likes3.getArticleid());
				articles.add((Article) articleMapper.selectAll(article).get(0));
			}
			return articles;
		}else {
			return null;
		}
		
	}

}
