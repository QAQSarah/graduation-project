package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ArticleMapper;
import com.sarah.dao.CollectMapper;
import com.sarah.model.Article;
import com.sarah.model.Collect;
import com.sarah.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	private CollectMapper collectMapper;
	@Autowired
	private ArticleMapper articleMapper;
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Collect record) {
		int r = 0;
		if (record != null) {
			List<Collect> list=selectAll(record);
			if(list.size()==0) {
				r = collectMapper.insert(record);
			}
		}
		return r;
	}

	public int insertSelective(Collect record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collect selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Collect record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Collect record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Collect> selectAll(Collect record) {
		List<Collect> list = new ArrayList<Collect>();
		if (record != null) {
			list = collectMapper.selectAll(record);
		}
		return list;
	}

	public int deleteByCollect(Collect record) {
		List<Collect> list = new ArrayList<Collect>();
		int r = 0;
		if (record != null) {
			list = selectAll(record);
			Collect l = new Collect();
			if (list.size() != 0) {
				l = list.get(0);
				r = collectMapper.deleteByPrimaryKey(l.getId());
			}
		}
		return r;
	}

	public List<Article> selectByUser(int userid) {
		Collect collect = new Collect();
		List<Collect> collects = new ArrayList<Collect>();
		List<Article> articles = new ArrayList<Article>();
		if (userid != 0) {
			collect.setUid(userid);
			collects = collectMapper.selectAll(collect);
		}
		List<Integer> aList = new ArrayList<Integer>();
		if (collects.size() != 0) {
			for (Collect collect2 : collects) {
				aList.add(collect2.getArticleid());
			}
			List<Integer> listWithoutDup = new ArrayList<Integer>(new HashSet<Integer>(aList));

			for (Integer integer : listWithoutDup) {
				articles.add(articleMapper.selectByPrimaryKey(integer));
			}
			
			return articles;
		} else {
			return null;
		}

	}

}
